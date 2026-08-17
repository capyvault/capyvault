package dev.capyvault.core.secret.application.port.out;

public record EncryptedSecretPayload(
        String ciphertext,
        String keyId,
        String algorithm,
        String nonce
) {
}