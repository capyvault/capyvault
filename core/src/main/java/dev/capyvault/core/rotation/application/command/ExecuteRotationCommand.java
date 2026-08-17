package dev.capyvault.core.rotation.application.command;

import java.util.UUID;

public record ExecuteRotationCommand(
        UUID secretId,
        String manualValue
) {
}