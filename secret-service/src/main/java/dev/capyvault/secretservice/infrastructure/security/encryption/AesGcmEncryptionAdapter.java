package dev.capyvault.secretservice.infrastructure.security.encryption;

import dev.capyvault.secretservice.application.port.out.EncryptionPort;
import dev.capyvault.secretservice.application.query.EncryptedValue;
import dev.capyvault.secretservice.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Component
public class AesGcmEncryptionAdapter implements EncryptionPort {
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int IV_LENGTH = 12;
    private static final int TAG_LENGTH_BITS = 128;

    private final String keyId;
    private final byte[] secretKey;
    private final SecureRandom secureRandom = new SecureRandom();

    public AesGcmEncryptionAdapter(@Value("${capyvault.encryption.key-id}") String keyId,
                                   @Value("${capyvault.encryption.secret-key}") String secretKey) {
        this.keyId = keyId;
        this.secretKey = secretKey.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public EncryptedValue encrypt(String plainText) {
        try {
            byte[] iv = new byte[IV_LENGTH];
            secureRandom.nextBytes(iv);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secretKey, "AES"), new GCMParameterSpec(TAG_LENGTH_BITS, iv));
            byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
            ByteBuffer buffer = ByteBuffer.allocate(iv.length + encrypted.length);
            buffer.put(iv);
            buffer.put(encrypted);
            return new EncryptedValue(Base64.getEncoder().encodeToString(buffer.array()), keyId);
        } catch (Exception ex) {
            throw new BusinessException("ENCRYPTION_FAILED", "Failed to encrypt secret value");
        }
    }

    @Override
    public String decrypt(String ciphertext) {
        try {
            byte[] decoded = Base64.getDecoder().decode(ciphertext);
            ByteBuffer buffer = ByteBuffer.wrap(decoded);
            byte[] iv = new byte[IV_LENGTH];
            buffer.get(iv);
            byte[] encrypted = new byte[buffer.remaining()];
            buffer.get(encrypted);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secretKey, "AES"), new GCMParameterSpec(TAG_LENGTH_BITS, iv));
            return new String(cipher.doFinal(encrypted), StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new BusinessException("DECRYPTION_FAILED", "Failed to decrypt secret value");
        }
    }
}
