package dev.capyvault.core.secret.application.query;

import java.time.Instant;
import java.util.UUID;

public record SecretVersionResult(UUID uuid, int versionNumber, String encryptionKeyId, UUID createdBy, Instant createdAt) {}
