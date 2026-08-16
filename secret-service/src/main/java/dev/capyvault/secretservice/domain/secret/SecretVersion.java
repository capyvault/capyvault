package dev.capyvault.secretservice.domain.secret;

import java.time.Instant;
import java.util.UUID;

public class SecretVersion {
    private UUID uuid;
    private int versionNumber;
    private String encryptedValue;
    private String encryptionKeyId;
    private UUID createdBy;
    private Instant createdAt;

    public static SecretVersion create(UUID uuid, int versionNumber, String encryptedValue, String encryptionKeyId, UUID createdBy) {
        SecretVersion version = new SecretVersion();
        version.uuid = uuid;
        version.versionNumber = versionNumber;
        version.encryptedValue = encryptedValue;
        version.encryptionKeyId = encryptionKeyId;
        version.createdBy = createdBy;
        version.createdAt = Instant.now();
        return version;
    }

    public UUID getUuid() { return uuid; }
    public int getVersionNumber() { return versionNumber; }
    public String getEncryptedValue() { return encryptedValue; }
    public String getEncryptionKeyId() { return encryptionKeyId; }
    public UUID getCreatedBy() { return createdBy; }
    public Instant getCreatedAt() { return createdAt; }

    public void restore(UUID uuid, int versionNumber, String encryptedValue, String encryptionKeyId, UUID createdBy, Instant createdAt) {
        this.uuid = uuid; this.versionNumber = versionNumber; this.encryptedValue = encryptedValue;
        this.encryptionKeyId = encryptionKeyId; this.createdBy = createdBy; this.createdAt = createdAt;
    }
}
