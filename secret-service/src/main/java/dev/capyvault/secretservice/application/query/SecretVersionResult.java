package dev.capyvault.secretservice.application.query;

import java.time.Instant;
import java.util.UUID;

public record SecretVersionResult(UUID uuid, int versionNumber, String encryptionKeyId, UUID createdBy, Instant createdAt) {}
