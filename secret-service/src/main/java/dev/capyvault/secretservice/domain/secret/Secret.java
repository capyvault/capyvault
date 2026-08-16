package dev.capyvault.secretservice.domain.secret;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class Secret {
    private UUID uuid;
    private UUID projectUuid;
    private UUID environmentUuid;
    private String key;
    private String description;
    private SecretStatus status;
    private UUID createdBy;
    private Instant createdAt;
    private Instant updatedAt;
    private List<SecretVersion> versions = new ArrayList<>();

    public static Secret create(UUID uuid, UUID projectUuid, UUID environmentUuid, String key, String description, UUID createdBy) {
        Secret secret = new Secret();
        secret.uuid = uuid;
        secret.projectUuid = projectUuid;
        secret.environmentUuid = environmentUuid;
        secret.key = key.trim().toUpperCase();
        secret.description = description;
        secret.status = SecretStatus.ACTIVE;
        secret.createdBy = createdBy;
        secret.createdAt = Instant.now();
        secret.updatedAt = Instant.now();
        return secret;
    }

    public void addVersion(String encryptedValue, String encryptionKeyId, UUID actorUuid) {
        int nextVersion = versions.stream().map(SecretVersion::getVersionNumber).max(Integer::compareTo).orElse(0) + 1;
        versions.add(SecretVersion.create(UUID.randomUUID(), nextVersion, encryptedValue, encryptionKeyId, actorUuid));
        this.updatedAt = Instant.now();
    }

    public void updateMetadata(String description) {
        this.description = description;
        this.updatedAt = Instant.now();
    }

    public void disable() { this.status = SecretStatus.DISABLED; this.updatedAt = Instant.now(); }
    public void delete() { this.status = SecretStatus.DELETED; this.updatedAt = Instant.now(); }

    public SecretVersion latestVersion() {
        return versions.stream().max(Comparator.comparingInt(SecretVersion::getVersionNumber))
                .orElseThrow(() -> new IllegalStateException("Secret has no version"));
    }

    public UUID getUuid() { return uuid; }
    public UUID getProjectUuid() { return projectUuid; }
    public UUID getEnvironmentUuid() { return environmentUuid; }
    public String getKey() { return key; }
    public String getDescription() { return description; }
    public SecretStatus getStatus() { return status; }
    public UUID getCreatedBy() { return createdBy; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public List<SecretVersion> getVersions() { return versions; }

    public void restore(UUID uuid, UUID projectUuid, UUID environmentUuid, String key, String description, SecretStatus status,
                        UUID createdBy, Instant createdAt, Instant updatedAt, List<SecretVersion> versions) {
        this.uuid = uuid; this.projectUuid = projectUuid; this.environmentUuid = environmentUuid; this.key = key;
        this.description = description; this.status = status; this.createdBy = createdBy; this.createdAt = createdAt;
        this.updatedAt = updatedAt; this.versions = versions == null ? new ArrayList<>() : versions;
    }
}
