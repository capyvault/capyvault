package dev.capyvault.accesscontrolservice.application.handler;

import dev.capyvault.accesscontrolservice.application.command.CreateGrantCommand;
import dev.capyvault.accesscontrolservice.application.port.out.AccessGrantRepository;
import dev.capyvault.accesscontrolservice.application.port.out.AuditEventPublisher;
import dev.capyvault.accesscontrolservice.application.port.out.ProjectClient;
import dev.capyvault.accesscontrolservice.domain.AccessGrant;
import dev.capyvault.accesscontrolservice.domain.AccessScopeType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class CreateGrantHandler {

    private final AccessGrantRepository repository;
    private final ProjectClient projectClient;
    private final AuditEventPublisher auditEventPublisher;

    public CreateGrantHandler(
            AccessGrantRepository repository,
            @Qualifier("fakeProjectRestClient") ProjectClient projectClient,
            AuditEventPublisher auditEventPublisher
    ) {
        this.repository = repository;
        this.projectClient = projectClient;
        this.auditEventPublisher = auditEventPublisher;
    }

    @Transactional
    public AccessGrant handle(CreateGrantCommand command) {

        if (!projectClient.projectExists(command.projectId())) {
            throw new IllegalArgumentException("Project does not exist");
        }

        if (command.scopeType() == AccessScopeType.ENVIRONMENT) {
            if (!projectClient.environmentExists(command.projectId(), command.environment())) {
                throw new IllegalArgumentException("Environment does not exist in this project");
            }
        }

        boolean duplicate = repository.existsDuplicate(
                command.principalId(),
                command.principalType(),
                command.projectId(),
                command.environment()
        );

        if (duplicate) {
            throw new IllegalStateException("Access grant already exists");
        }

        AccessGrant grant = AccessGrant.create(
                command.principalId(),
                command.principalType(),
                command.projectId(),
                command.environment(),
                command.scopeType(),
                command.role(),
                command.effect(),
                command.validFrom(),
                command.validUntil(),
                command.createdBy()
        );

        AccessGrant saved = repository.save(grant);

        auditEventPublisher.publish(
                "ACCESS_GRANTED",
                command.createdBy(),
                command.projectId(),
                command.environment(),
                Map.of(
                        "grantId", saved.getUuid().toString(),
                        "principalId", command.principalId().toString(),
                        "principalType", command.principalType().name(),
                        "scopeType", command.scopeType().name(),
                        "role", command.role().name(),
                        "effect", command.effect().name()
                )
        );

        return saved;
    }
}