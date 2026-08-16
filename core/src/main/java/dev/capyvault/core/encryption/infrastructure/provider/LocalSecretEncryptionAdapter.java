package dev.capyvault.core.encryption.infrastructure.provider;

import dev.capyvault.core.encryption.domain.EncryptionAlgorithm;
import dev.capyvault.core.secret.application.port.out.SecretEncryptionPort;
import dev.capyvault.core.secret.domain.EncryptedSecretValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class LocalSecretEncryptionAdapter implements SecretEncryptionPort {

    private static final int GCM_TAG_LENGTH = 128;
    private static final int NONCE_LENGTH_BYTES = 12;

    private final String keyId;
    private final SecretKeySpec secretKeySpec;
    private final SecureRandom secureRandom = new SecureRandom();

    public LocalSecretEncryptionAdapter(
            @Value("${capyvault.encryption.key-id}") String keyId,
            @Value("${capyvault.encryption.secret-key}") String rawSecretKey
    ) {
        this.keyId = keyId;
        this.secretKeySpec = new SecretKeySpec(validateKey(rawSecretKey), "AES");
    }

    @Override
    public EncryptedSecretValue encrypt(String plaintext) {
        try {
            byte[] nonce = new byte[NONCE_LENGTH_BYTES];
            secureRandom.nextBytes(nonce);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(
                    GCM_TAG_LENGTH,
                    nonce
            );

            cipher.init(
                    Cipher.ENCRYPT_MODE,
                    secretKeySpec,
                    gcmParameterSpec
            );

            byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());

            return new EncryptedSecretValue(
                    Base64.getEncoder().encodeToString(encryptedBytes),
                    keyId,
                    EncryptionAlgorithm.AES_256_GCM.name(),
                    Base64.getEncoder().encodeToString(nonce)
            );
        } catch (Exception exception) {
            throw new IllegalStateException("Failed to encrypt secret value", exception);
        }
    }

    @Override
    public String decrypt(EncryptedSecretValue encryptedValue) {
        try {
            byte[] nonce = Base64.getDecoder().decode(encryptedValue.nonce());
            byte[] ciphertext = Base64.getDecoder().decode(encryptedValue.ciphertext());

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(
                    GCM_TAG_LENGTH,
                    nonce
            );

            cipher.init(
                    Cipher.DECRYPT_MODE,
                    secretKeySpec,
                    gcmParameterSpec
            );

            byte[] plaintextBytes = cipher.doFinal(ciphertext);

            return new String(plaintextBytes);
        } catch (Exception exception) {
            throw new IllegalStateException("Failed to decrypt secret value", exception);
        }
    }

    private byte[] validateKey(String rawSecretKey) {
        byte[] keyBytes = rawSecretKey.getBytes();

        if (keyBytes.length != 32) {
            throw new IllegalArgumentException(
                    "capyvault.encryption.secret-key must be 32 characters for AES-256"
            );
        }

        return keyBytes;
    }
}