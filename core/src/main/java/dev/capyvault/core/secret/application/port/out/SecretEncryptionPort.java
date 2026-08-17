package dev.capyvault.core.secret.application.port.out;


public interface SecretEncryptionPort {

    EncryptedSecretPayload encrypt(String plaintext);

    String decrypt(EncryptedSecretPayload encryptedValue);
}