package dev.capyvault.secretservice.application.query;

import dev.capyvault.secretservice.domain.secret.SecretStatus;
import java.time.Instant;
import java.util.UUID;

public record SecretResult(UUID uuid, UUID projectUuid, UUID environmentUuid, String key, String description,
                           SecretStatus status, int latestVersion, Instant createdAt, Instant updatedAt) {}
