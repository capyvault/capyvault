package dev.capyvault.accesscontrolservice.domain;

import java.time.Instant;
import java.util.UUID;

public class AccessGrant {

    private final Long id;
    private final UUID uuid;

    private final UUID principalId;
    private final PrincipalType principalType;

    private final UUID projectId;
    private final String environment;
    private final AccessScopeType scopeType;

    private final GrantRole role;
    private final AccessEffect effect;
    private final GrantStatus status;

    private final Instant validFrom;
    private final Instant validUntil;

    private final UUID createdBy;
    private final Instant createdAt;
    private final Instant updatedAt;

    public AccessGrant(
            Long id,
            UUID uuid,
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
        this.id = id;
        this.uuid = uuid;
        this.principalId = principalId;
        this.principalType = principalType;
        this.projectId = projectId;
        this.environment = environment;
        this.scopeType = scopeType;
        this.role = role;
        this.effect = effect;
        this.status = status;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static AccessGrant create(
            UUID principalId,
            PrincipalType principalType,
            UUID projectId,
            String environment,
            AccessScopeType scopeType,
            GrantRole role,
            AccessEffect effect,
            Instant validFrom,
            Instant validUntil,
            UUID createdBy
    ) {
        Instant now = Instant.now();

        if (scopeType == AccessScopeType.ENVIRONMENT && (environment == null || environment.isBlank())) {
            throw new IllegalArgumentException("Environment is required for ENVIRONMENT scope");
        }

        if (scopeType == AccessScopeType.PROJECT && environment != null && !environment.isBlank()) {
            throw new IllegalArgumentException("Environment must be empty for PROJECT scope");
        }

        if (validFrom != null && validUntil != null && validUntil.isBefore(validFrom)) {
            throw new IllegalArgumentException("validUntil must be after validFrom");
        }

        return new AccessGrant(
                null,
                UUID.randomUUID(),
                principalId,
                principalType,
                projectId,
                normalizeEnvironment(environment),
                scopeType,
                role,
                effect,
                GrantStatus.ACTIVE,
                validFrom,
                validUntil,
                createdBy,
                now,
                now
        );
    }

    public AccessGrant update(
            GrantRole role,
            AccessEffect effect,
            GrantStatus status,
            Instant validFrom,
            Instant validUntil
    ) {
        if (validFrom != null && validUntil != null && validUntil.isBefore(validFrom)) {
            throw new IllegalArgumentException("validUntil must be after validFrom");
        }

        return new AccessGrant(
                this.id,
                this.uuid,
                this.principalId,
                this.principalType,
                this.projectId,
                this.environment,
                this.scopeType,
                role,
                effect,
                status,
                validFrom,
                validUntil,
                this.createdBy,
                this.createdAt,
                Instant.now()
        );
    }

    public boolean matchesScope(UUID projectId, String requestedEnvironment) {
        if (!this.projectId.equals(projectId)) {
            return false;
        }

        if (this.scopeType == AccessScopeType.PROJECT) {
            return true;
        }

        return this.environment != null
                && this.environment.equalsIgnoreCase(requestedEnvironment);
    }

    public boolean isCurrentlyActive(Instant now) {
        if (status != GrantStatus.ACTIVE) {
            return false;
        }

        if (validFrom != null && now.isBefore(validFrom)) {
            return false;
        }

        return validUntil == null || !now.isAfter(validUntil);
    }

    public boolean roleAllows(AccessAction action) {
        return role.can(action);
    }

    private static String normalizeEnvironment(String environment) {
        if (environment == null || environment.isBlank()) {
            return null;
        }
        return environment.trim().toLowerCase();
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UUID getPrincipalId() {
        return principalId;
    }

    public PrincipalType getPrincipalType() {
        return principalType;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public String getEnvironment() {
        return environment;
    }

    public AccessScopeType getScopeType() {
        return scopeType;
    }

    public GrantRole getRole() {
        return role;
    }

    public AccessEffect getEffect() {
        return effect;
    }

    public GrantStatus getStatus() {
        return status;
    }

    public Instant getValidFrom() {
        return validFrom;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}