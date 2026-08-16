package dev.capyvault.core.secret.application.query;

public record EncryptedValue(String ciphertext, String keyId) {}
