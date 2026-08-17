package dev.capyvault.accesscontrolservice.infrastructure.persistence.entity;

import dev.capyvault.accesscontrolservice.domain.*;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "access_grants",
        indexes = {
                @Index(name = "idx_access_grants_project_id", columnList = "project_id"),
                @Index(name = "idx_access_grants_principal", columnList = "principal_id, principal_type"),
                @Index(name = "idx_access_grants_project_principal", columnList = "project_id, principal_id, principal_type")
        }
)
public class AccessGrantJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @Column(name = "principal_id", nullable = false)
    private UUID principalId;

    @Enumerated(EnumType.STRING)
    @Column(name = "principal_type", nullable = false, length = 50)
    private PrincipalType principalType;

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    @Column(length = 50)
    private String environment;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope_type", nullable = false, length = 50)
    private AccessScopeType scopeType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private GrantRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private AccessEffect effect;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private GrantStatus status;

    private Instant validFrom;

    private Instant validUntil;

    @Column(name = "created_by", nullable = false)
    private UUID createdBy;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    protected AccessGrantJpaEntity() {
    }

    public AccessGrantJpaEntity(
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