package dev.capyvault.projectservice.api.environment;

import dev.capyvault.projectservice.application.query.EnvironmentResult;
import dev.capyvault.projectservice.domain.environment.EnvironmentStatus;

import java.time.Instant;
import java.util.UUID;

public record EnvironmentResponse(
        UUID uuid,
        UUID projectUuid,
        String name,
        String slug,
        EnvironmentStatus status,
        Instant createdAt,
        Instant updatedAt
) {
    public static EnvironmentResponse from(EnvironmentResult result) {
        return new EnvironmentResponse(
                result.uuid(),
                result.projectUuid(),
                result.name(),
                result.slug(),
                result.status(),
                result.createdAt(),
                result.updatedAt()
        );
    }
}
