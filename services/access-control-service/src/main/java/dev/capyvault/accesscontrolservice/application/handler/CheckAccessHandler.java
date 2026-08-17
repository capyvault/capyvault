package dev.capyvault.accesscontrolservice.application.handler;

import dev.capyvault.accesscontrolservice.application.command.CheckAccessCommand;
import dev.capyvault.accesscontrolservice.application.port.out.AccessGrantRepository;
import dev.capyvault.accesscontrolservice.application.port.out.AuditEventPublisher;
import dev.capyvault.accesscontrolservice.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class CheckAccessHandler {

    private final AccessGrantRepository repository;
    private final AuditEventPublisher auditEventPublisher;

    public CheckAccessHandler(
            AccessGrantRepository repository,
            AuditEventPublisher auditEventPublisher
    ) {
        this.repository = repository;
        this.auditEventPublisher = auditEventPublisher;
    }

    @Transactional(readOnly = true)
    public AccessDecision handle(CheckAccessCommand command) {
        Instant now = Instant.now();

        List<AccessGrant> grants = repository.findCandidateGrants(
                command.principalId(),
                command.principalType(),
                command.projectId()
        );

        List<AccessGrant> activeMatchingGrants = grants.stream()
                .filter(grant -> grant.matchesScope(command.projectId(), command.environment()))
                .filter(grant -> grant.isCurrentlyActive(now))
                .filter(grant -> grant.roleAllows(command.action()))
                .sorted(Comparator.comparing(this::scopePriority).reversed())
                .toList();

        AccessDecision decision;

        boolean hasDeny = activeMatchingGrants.stream()
                .anyMatch(grant -> grant.getEffect() == AccessEffect.DENY);

        if (hasDeny) {
            decision = AccessDecision.deny("Explicit DENY grant matched. DENY wins over ALLOW.");
        } else {
            boolean hasAllow = activeMatchingGrants.stream()
                    .anyMatch(grant -> grant.getEffect() == AccessEffect.ALLOW);

            if (hasAllow) {
                decision = AccessDecision.allow("Matching ALLOW grant found.");
            } else {
                decision = AccessDecision.deny("No active grant allows this action.");
            }
        }

        auditEventPublisher.publish(
                decision.allowed() ? "ACCESS_CHECK_ALLOWED" : "ACCESS_CHECK_DENIED",
                command.principalId(),
                command.projectId(),
                command.environment(),
                Map.of(
                        "principalType", command.principalType().name(),
                        "action", command.action().name(),
                        "decision", decision.decision().name(),
                        "reason", decision.reason()
                )
        );

        return decision;
    }

    private int scopePriority(AccessGrant grant) {
        if (grant.getScopeType() == AccessScopeType.ENVIRONMENT) {
            return 2;
        }
        return 1;
    }
}