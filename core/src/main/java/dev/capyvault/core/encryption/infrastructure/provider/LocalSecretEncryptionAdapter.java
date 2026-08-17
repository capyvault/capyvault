package dev.capyvault.core.encryption.infrastructure.provider;

import dev.capyvault.core.encryption.domain.EncryptionAlgorithm;
import dev.capyvault.core.secret.application.port.out.EncryptedSecretPayload;
import dev.capyvault.core.secret.application.port.out.SecretEncryptionPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class LocalSecretEncryptionAdapter implements SecretEncryptionPort {

    private static final int GCM_TAG_LENGTH_BITS = 128;
    private static final int NONCE_LENGTH_BYTES = 12;

    private final String keyId;
    private final SecretKeySpec secretKeySpec;
    private final SecureRandom secureRandom = new SecureRandom();

    public LocalSecretEncryptionAdapter(
            @Value("${capyvault.encryption.key-id}") String keyId,
            @Value("${capyvault.encryption.secret-key}") String rawSecretKey
    ) {
        this.keyId = keyId;
        this.secretKeySpec = new SecretKeySpec(
                validateKey(rawSecretKey),
                "AES"
        );
    }

    @Override
    public EncryptedSecretPayload encrypt(String plaintext) {
        try {
            byte[] nonce = new byte[NONCE_LENGTH_BYTES];
            secureRandom.nextBytes(nonce);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            cipher.init(
                    Cipher.ENCRYPT_MODE,
                    secretKeySpec,
                    new GCMParameterSpec(GCM_TAG_LENGTH_BITS, nonce)
            );

            byte[] ciphertext = cipher.doFinal(
                    plaintext.getBytes(StandardCharsets.UTF_8)
            );

            return new EncryptedSecretPayload(
                    Base64.getEncoder().encodeToString(ciphertext),
                    keyId,
                    EncryptionAlgorithm.AES_256_GCM.name(),
                    Base64.getEncoder().encodeToString(nonce)
            );
        } catch (Exception exception) {
            throw new IllegalStateException("Failed to encrypt secret value", exception);
        }
    }

    @Override
    public String decrypt(EncryptedSecretPayload encryptedPayload) {
        try {
            byte[] nonce = Base64.getDecoder().decode(encryptedPayload.nonce());
            byte[] ciphertext = Base64.getDecoder().decode(encryptedPayload.ciphertext());

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            cipher.init(
                    Cipher.DECRYPT_MODE,
                    secretKeySpec,
                    new GCMParameterSpec(GCM_TAG_LENGTH_BITS, nonce)
            );

            byte[] plaintext = cipher.doFinal(ciphertext);

            return new String(plaintext, StandardCharsets.UTF_8);
        } catch (Exception exception) {
            throw new IllegalStateException("Failed to decrypt secret value", exception);
        }
    }

    private byte[] validateKey(String rawSecretKey) {
        if (rawSecretKey == null || rawSecretKey.isBlank()) {
            throw new IllegalArgumentException("Encryption secret key is required");
        }

        byte[] keyBytes = rawSecretKey.getBytes(StandardCharsets.UTF_8);

        if (keyBytes.length != 32) {
            throw new IllegalArgumentException(
                    "capyvault.encryption.secret-key must be 32 characters for AES-256"
            );
        }

        return keyBytes;
    }
}