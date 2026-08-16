package dev.capyvault.core.secret.application.port.out;


import dev.capyvault.core.secret.application.query.EncryptedValue;

public interface EncryptionPort { EncryptedValue encrypt(String plainText); String decrypt(String ciphertext); }
