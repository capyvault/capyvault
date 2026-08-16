package dev.capyvault.projectservice.application.query;

import dev.capyvault.projectservice.domain.environment.EnvironmentStatus;

import java.time.Instant;
import java.util.UUID;

public record EnvironmentResult(
        UUID uuid,
        UUID projectUuid,
        String name,
        String slug,
        EnvironmentStatus status,
        Instant createdAt,
        Instant updatedAt
) {
}
