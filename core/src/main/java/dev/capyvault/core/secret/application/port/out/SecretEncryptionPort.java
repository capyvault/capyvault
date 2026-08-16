package dev.capyvault.core.secret.application.port.out;

import dev.capyvault.core.secret.domain.EncryptedSecretValue;

public interface SecretEncryptionPort {

    EncryptedSecretValue encrypt(String plaintext);

    String decrypt(EncryptedSecretValue encryptedValue);
}