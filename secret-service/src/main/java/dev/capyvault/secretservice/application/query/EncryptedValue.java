package dev.capyvault.secretservice.application.query;

public record EncryptedValue(String ciphertext, String keyId) {}
