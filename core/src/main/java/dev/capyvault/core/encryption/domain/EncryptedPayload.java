package dev.capyvault.core.encryption.domain;

public record EncryptedPayload(
        String ciphertext,
        String keyId,
        String algorithm,
        String nonce
) {
}