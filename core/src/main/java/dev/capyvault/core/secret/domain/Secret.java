package dev.capyvault.core.secret.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class Secret {

    private final UUID id;
    private final UUID projectId;
    private final UUID environmentId;
    private final String name;
    private final SecretType type;
    private SecretStatus status;
    private final List<SecretVersion> versions;
    private final Instant createdAt;
    private Instant updatedAt;

    private Secret(
            UUID id,
            UUID projectId,
            UUID environmentId,
            String name,
            SecretType type,
            SecretStatus status,
            List<SecretVersion> versions,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = Objects.requireNonNull(id);
        this.projectId = Objects.requireNonNull(projectId);
        this.environmentId = Objects.requireNonNull(environmentId);
        this.name = validateName(name);
        this.type = Objects.requireNonNull(type);
        this.status = Objects.requireNonNull(status);
        this.versions = new ArrayList<>(Objects.requireNonNull(versions));
        this.createdAt = Objects.requireNonNull(createdAt);
        this.updatedAt = Objects.requireNonNull(updatedAt);
    }

    public static Secret create(
            UUID projectId,
            UUID environmentId,
            String name,
            SecretType type,
            EncryptedSecretValue encryptedValue
    ) {
        Instant now = Instant.now();

        SecretVersion firstVersion = SecretVersion.firstVersion(encryptedValue);

        return new Secret(
                UUID.randomUUID(),
                projectId,
                environmentId,
                name,
                type,
                SecretStatus.ACTIVE,
                List.of(firstVersion),
                now,
                now
        );
    }

    public void disable() {
        if (this.status == SecretStatus.DELETED) {
            throw new IllegalStateException("Deleted secret cannot be disabled");
        }

        this.status = SecretStatus.DISABLED;
        this.updatedAt = Instant.now();
    }

    public void delete() {
        this.status = SecretStatus.DELETED;
        this.updatedAt = Instant.now();
    }

    private static String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Secret name is required");
        }

        if (name.length() > 150) {
            throw new IllegalArgumentException("Secret name must not exceed 150 characters");
        }

        return name.trim();
    }

    public UUID id() {
        return id;
    }

    public UUID projectId() {
        return projectId;
    }

    public UUID environmentId() {
        return environmentId;
    }

    public String name() {
        return name;
    }

    public SecretType type() {
        return type;
    }

    public SecretStatus status() {
        return status;
    }

    public List<SecretVersion> versions() {
        return List.copyOf(versions);
    }

    public SecretVersion currentVersion() {
        return versions.stream()
                .filter(SecretVersion::current)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Secret has no current version"));
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant updatedAt() {
        return updatedAt;
    }
}