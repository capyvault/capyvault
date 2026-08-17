package dev.capyvault.core.rotation.application.command;

import dev.capyvault.core.rotation.domain.RotationStrategy;

import java.util.UUID;

public record CreateRotationPolicyCommand(
        UUID secretId,
        int intervalDays,
        RotationStrategy strategy
) {
}