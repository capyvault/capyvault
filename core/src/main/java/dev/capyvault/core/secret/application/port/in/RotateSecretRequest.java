package dev.capyvault.core.secret.application.port.in;

import java.util.UUID;

public record RotateSecretRequest(
        UUID secretId,
        String rotatedValue
) {
}
