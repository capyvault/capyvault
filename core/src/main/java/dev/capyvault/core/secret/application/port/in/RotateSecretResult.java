package dev.capyvault.core.secret.application.port.in;


import java.time.Instant;
import java.util.UUID;

public record RotateSecretResult(
        UUID secretId,
        int currentVersion,
        Instant rotatedAt
) {
}