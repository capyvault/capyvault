package dev.capyvault.accesscontrolservice.application.handler;

import dev.capyvault.accesscontrolservice.application.command.UpdateGrantCommand;
import dev.capyvault.accesscontrolservice.application.port.out.AccessGrantRepository;
import dev.capyvault.accesscontrolservice.application.port.out.AuditEventPublisher;
import dev.capyvault.accesscontrolservice.domain.AccessGrant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class UpdateGrantHandler {

    private final AccessGrantRepository repository;
    private final AuditEventPublisher auditEventPublisher;

    public UpdateGrantHandler(
            AccessGrantRepository repository,
            AuditEventPublisher auditEventPublisher
    ) {
        this.repository = repository;
        this.auditEventPublisher = auditEventPublisher;
    }

    @Transactional
    public AccessGrant handle(UpdateGrantCommand command) {
        AccessGrant existing = repository.findByUuid(command.grantId())
                .orElseThrow(() -> new IllegalArgumentException("Access grant not found"));

        AccessGrant updated = existing.update(
                command.role(),
                command.effect(),
                command.status(),
                command.validFrom(),
                command.validUntil()
        );

        AccessGrant saved = repository.save(updated);

        auditEventPublisher.publish(
                "ACCESS_UPDATED",
                command.actorId(),
                saved.getProjectId(),
                saved.getEnvironment(),
                Map.of(
                        "grantId", saved.getUuid().toString(),
                        "principalId", saved.getPrincipalId().toString(),
                        "principalType", saved.getPrincipalType().name(),
                        "role", saved.getRole().name(),
                        "effect", saved.getEffect().name(),
                        "status", saved.getStatus().name()
                )
        );

        return saved;
    }
}