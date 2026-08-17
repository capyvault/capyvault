package dev.capyvault.core.secret.domain;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class SecretVersion {

    private final UUID id;
    private final int versionNumber;
    private final EncryptedSecretValue encryptedValue;
    private boolean current;
    private final Instant createdAt;

    private SecretVersion(
            UUID id,
            int versionNumber,
            EncryptedSecretValue encryptedValue,
            boolean current,
            Instant createdAt
    ) {
        if (versionNumber < 1) {
            throw new IllegalArgumentException("Version number must be greater than zero");
        }

        this.id = Objects.requireNonNull(id);
        this.versionNumber = versionNumber;
        this.encryptedValue = Objects.requireNonNull(encryptedValue);
        this.current = current;
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public static SecretVersion create(
            int versionNumber,
            EncryptedSecretValue encryptedValue,
            boolean current
    ) {
        return new SecretVersion(
                UUID.randomUUID(),
                versionNumber,
                encryptedValue,
                current,
                Instant.now()
        );
    }

    public static SecretVersion restore(
            UUID id,
            int versionNumber,
            EncryptedSecretValue encryptedValue,
            boolean current,
            Instant createdAt
    ) {
        return new SecretVersion(
                id,
                versionNumber,
                encryptedValue,
                current,
                createdAt
        );
    }

    public void markCurrent() {
        this.current = true;
    }

    public void markNotCurrent() {
        this.current = false;
    }

    public UUID id() {
        return id;
    }

    public int versionNumber() {
        return versionNumber;
    }

    public EncryptedSecretValue encryptedValue() {
        return encryptedValue;
    }

    public boolean current() {
        return current;
    }

    public Instant createdAt() {
        return createdAt;
    }
}