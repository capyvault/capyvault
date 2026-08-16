package dev.capyvault.core.secret.web;

import dev.capyvault.core.secret.application.query.SecretVersionResult;

import java.time.Instant;
import java.util.UUID;

public record SecretVersionResponse(UUID uuid, int versionNumber, String encryptionKeyId, UUID createdBy, Instant createdAt) { public static SecretVersionResponse from(SecretVersionResult r) { return new SecretVersionResponse(r.uuid(), r.versionNumber(), r.encryptionKeyId(), r.createdBy(), r.createdAt()); } }
