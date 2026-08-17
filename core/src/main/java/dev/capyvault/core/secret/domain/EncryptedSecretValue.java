package dev.capyvault.core.secret.domain;

import dev.capyvault.core.secret.application.port.out.EncryptedSecretPayload;

import java.util.Objects;

public final class EncryptedSecretValue {

    private final String ciphertext;
    private final String keyId;
    private final String algorithm;
    private final String nonce;

    public EncryptedSecretValue(
            String ciphertext,
            String keyId,
            String algorithm,
            String nonce
    ) {
        this.ciphertext = Objects.requireNonNull(ciphertext, "ciphertext is required");
        this.keyId = Objects.requireNonNull(keyId, "keyId is required");
        this.algorithm = Objects.requireNonNull(algorithm, "algorithm is required");
        this.nonce = Objects.requireNonNull(nonce, "nonce is required");
    }

    public static EncryptedSecretValue from(EncryptedSecretPayload payload) {
        return new EncryptedSecretValue(
                payload.ciphertext(),
                payload.keyId(),
                payload.algorithm(),
                payload.nonce()
        );
    }

    public EncryptedSecretPayload toPayload() {
        return new EncryptedSecretPayload(
                ciphertext,
                keyId,
                algorithm,
                nonce
        );
    }

    public String ciphertext() {
        return ciphertext;
    }

    public String keyId() {
        return keyId;
    }

    public String algorithm() {
        return algorithm;
    }

    public String nonce() {
        return nonce;
    }
}