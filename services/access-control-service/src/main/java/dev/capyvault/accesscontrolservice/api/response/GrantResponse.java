package dev.capyvault.accesscontrolservice.api.response;

import dev.capyvault.accesscontrolservice.domain.*;

import java.time.Instant;
import java.util.UUID;

public record GrantResponse(
        UUID id,
        UUID principalId,
        PrincipalType principalType,
        UUID projectId,
        String environment,
        AccessScopeType scopeType,
        GrantRole role,
        AccessEffect effect,
        GrantStatus status,
        Instant validFrom,
        Instant validUntil,
        UUID createdBy,
        Instant createdAt,
        Instant updatedAt
) {
    public static GrantResponse from(AccessGrant grant) {
        return new GrantResponse(
                grant.getUuid(),
                grant.getPrincipalId(),
                grant.getPrincipalType(),
                grant.getProjectId(),
                grant.getEnvironment(),
                grant.getScopeType(),
                grant.getRole(),
                grant.getEffect(),
                grant.getStatus(),
                grant.getValidFrom(),
                grant.getValidUntil(),
                grant.getCreatedBy(),
                grant.getCreatedAt(),
                grant.getUpdatedAt()
        );
    }
}