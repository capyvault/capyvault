package dev.capyvault.core.rotation.web;
import java.time.Instant;
import java.util.UUID;

public record RotationExecutionResponse(
        UUID secretId,
        int newVersion,
        Instant rotatedAt,
        Instant nextRotationAt
) {
}
