package dev.capyvault.accesscontrolservice.domain;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class AccessGrant {

    private final Long id;
    private final UUID uuid;
    private final UUID projectId;
    private final UUID userId;
    private final String environment;
    private final GrantRole role;
    private final GrantStatus status;
    private final Instant createdAt;
    private final Instant updatedAt;

    public AccessGrant(
            Long id,
            UUID uuid,
            UUID projectId,
            UUID userId,
            String environment,
            GrantRole role,
            GrantStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.uuid = uuid;
        this.projectId = projectId;
        this.userId = userId;
        this.environment = environment;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static AccessGrant create(
            UUID projectId,
            UUID userId,
            String environment,
            GrantRole role
    ) {
        Instant now = Instant.now();

        return new AccessGrant(
                null,
                UUID.randomUUID(),
                projectId,
                userId,
                environment,
                role,
                GrantStatus.ACTIVE,
                now,
                now
        );
    }

    public AccessGrant update(GrantRole role, GrantStatus status) {
        return new AccessGrant(
                this.id,
                this.uuid,
                this.projectId,
                this.userId,
                this.environment,
                role,
                status,
                this.createdAt,
                Instant.now()
        );
    }

    public boolean allows(AccessAction action) {
        return this.status == GrantStatus.ACTIVE && this.role.can(action);
    }

}
