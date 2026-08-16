package dev.capyvault.accesscontrolservice.infrastructure.persistence.entity;

import dev.capyvault.accesscontrolservice.domain.GrantRole;
import dev.capyvault.accesscontrolservice.domain.GrantStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "access_grants",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_access_grant_project_user_env",
                        columnNames = {"project_id", "user_id", "environment"}
                )
        },
        indexes = {
                @Index(name = "idx_access_grants_project_id", columnList = "project_id"),
                @Index(name = "idx_access_grants_user_id", columnList = "user_id")
        }
)
public class AccessGrantJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(nullable = false, length = 50)
    private String environment;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private GrantRole role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private GrantStatus status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    protected AccessGrantJpaEntity() {
    }

    public AccessGrantJpaEntity(
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

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getEnvironment() {
        return environment;
    }

    public GrantRole getRole() {
        return role;
    }

    public GrantStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}