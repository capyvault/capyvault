package dev.capyvault.core.secret.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(
        name = "secrets",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_secret_project_env_name",
                        columnNames = {"project_id", "environment_id", "name"}
                )
        }
)
public class SecretJpaEntity {

    @Id
    private UUID id;

    @Column(name = "project_id", nullable = false)
    private UUID projectId;

    @Column(name = "environment_id", nullable = false)
    private UUID environmentId;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, length = 50)
    private String status;

    @OneToMany(
            mappedBy = "secret",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SecretVersionJpaEntity> versions = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected SecretJpaEntity() {
    }

    public SecretJpaEntity(
            UUID id,
            UUID projectId,
            UUID environmentId,
            String name,
            String type,
            String status,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.projectId = projectId;
        this.environmentId = environmentId;
        this.name = name;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void addVersion(SecretVersionJpaEntity version) {
        version.setSecret(this);
        this.versions.add(version);
    }

    public UUID getId() {
        return id;
    }

    public UUID getProjectId() {
        return projectId;
    }

    public UUID getEnvironmentId() {
        return environmentId;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public List<SecretVersionJpaEntity> getVersions() {
        return versions;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}