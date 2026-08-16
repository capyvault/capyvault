package dev.capyvault.accesscontrolservice.api.response;

import dev.capyvault.accesscontrolservice.domain.AccessGrant;
import dev.capyvault.accesscontrolservice.domain.GrantRole;
import dev.capyvault.accesscontrolservice.domain.GrantStatus;

import java.time.Instant;
import java.util.UUID;

public record GrantResponse(
        UUID id,
        UUID projectId,
        UUID userId,
        String environment,
        GrantRole role,
        GrantStatus status,
        Instant createdAt,
        Instant updatedAt
) {
    public static GrantResponse from(AccessGrant grant) {
        return new GrantResponse(
                grant.getUuid(),
                grant.getProjectId(),
                grant.getUserId(),
                grant.getEnvironment(),
                grant.getRole(),
                grant.getStatus(),
                grant.getCreatedAt(),
                grant.getUpdatedAt()
        );
    }
}