package dev.capyvault.secretservice.application.port.out;

import dev.capyvault.secretservice.application.query.EncryptedValue;

public interface EncryptionPort { EncryptedValue encrypt(String plainText); String decrypt(String ciphertext); }
