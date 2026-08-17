package dev.capyvault.core.secret.application.command;

import java.util.UUID;

public record RotateSecretCommand(
        UUID secretId,
        String rotatedValue
) {
}