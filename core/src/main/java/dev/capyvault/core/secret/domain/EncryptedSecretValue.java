package dev.capyvault.core.secret.domain;

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
        this.ciphertext = Objects.requireNonNull(ciphertext);
        this.keyId = Objects.requireNonNull(keyId);
        this.algorithm = Objects.requireNonNull(algorithm);
        this.nonce = Objects.requireNonNull(nonce);
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
