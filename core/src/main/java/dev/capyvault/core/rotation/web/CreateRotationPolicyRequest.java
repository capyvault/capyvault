package dev.capyvault.core.rotation.web;

import dev.capyvault.core.rotation.domain.RotationStrategy;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateRotationPolicyRequest(

        @NotNull(message = "Secret id is required")
        UUID secretId,

        @Min(value = 1, message = "Rotation interval must be at least 1 day")
        int intervalDays,

        @NotNull(message = "Rotation strategy is required")
        RotationStrategy strategy
) {
}
