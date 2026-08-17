package dev.capyvault.accesscontrolservice.application.handler;

import dev.capyvault.accesscontrolservice.application.port.out.AccessGrantRepository;
import dev.capyvault.accesscontrolservice.application.port.out.AuditEventPublisher;
import dev.capyvault.accesscontrolservice.domain.AccessGrant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.UUID;

@Service
public class DeleteGrantHandler {

    private final AccessGrantRepository repository;
    private final AuditEventPublisher auditEventPublisher;

    public DeleteGrantHandler(
            AccessGrantRepository repository,
            AuditEventPublisher auditEventPublisher
    ) {
        this.repository = repository;
        this.auditEventPublisher = auditEventPublisher;
    }

    @Transactional
    public void handle(UUID grantId, UUID actorId) {
        AccessGrant grant = repository.findByUuid(grantId)
                .orElseThrow(() -> new IllegalArgumentException("Access grant not found"));

        repository.deleteByUuid(grantId);

        auditEventPublisher.publish(
                "ACCESS_REVOKED",
                actorId,
                grant.getProjectId(),
                grant.getEnvironment(),
                Map.of(
                        "grantId", grant.getUuid().toString(),
                        "principalId", grant.getPrincipalId().toString(),
                        "principalType", grant.getPrincipalType().name()
                )
        );
    }
}