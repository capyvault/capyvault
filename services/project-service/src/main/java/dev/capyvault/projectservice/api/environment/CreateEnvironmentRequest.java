package dev.capyvault.projectservice.api.environment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateEnvironmentRequest(
        @NotBlank(message = "Environment name is required")
        @Size(max = 100, message = "Environment name must not exceed 100 characters")
        String name,

        @NotBlank(message = "Environment slug is required")
        @Size(max = 50, message = "Environment slug must not exceed 50 characters")
        String slug
) {
}
