package dev.capyvault.secretservice.api.secret;

import dev.capyvault.secretservice.application.query.SecretResult;
import dev.capyvault.secretservice.domain.secret.SecretStatus;
import java.time.Instant;
import java.util.UUID;

public record SecretResponse(UUID uuid, UUID projectUuid, UUID environmentUuid, String key, String description,
                             SecretStatus status, int latestVersion, Instant createdAt, Instant updatedAt) {
    public static SecretResponse from(SecretResult result) {
        return new SecretResponse(result.uuid(), result.projectUuid(), result.environmentUuid(), result.key(),
                result.description(), result.status(), result.latestVersion(), result.createdAt(), result.updatedAt());
    }
}
