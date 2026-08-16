package dev.capyvault.core.secret.domain;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class SecretVersion {

    private final UUID id;
    private final int versionNumber;
    private final EncryptedSecretValue encryptedValue;
    private final boolean current;
    private final Instant createdAt;

    private SecretVersion(
            UUID id,
            int versionNumber,
            EncryptedSecretValue encryptedValue,
            boolean current,
            Instant createdAt
    ) {
        if (versionNumber < 1) {
            throw new IllegalArgumentException("Secret version number must be greater than zero");
        }

        this.id = Objects.requireNonNull(id);
        this.versionNumber = versionNumber;
        this.encryptedValue = Objects.requireNonNull(encryptedValue);
        this.current = current;
        this.createdAt = Objects.requireNonNull(createdAt);
    }

    public static SecretVersion firstVersion(EncryptedSecretValue encryptedValue) {
        return new SecretVersion(
                UUID.randomUUID(),
                1,
                encryptedValue,
                true,
                Instant.now()
        );
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