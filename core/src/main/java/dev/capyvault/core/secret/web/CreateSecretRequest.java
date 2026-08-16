package dev.capyvault.core.secret.web;

import dev.capyvault.core.secret.domain.SecretType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CreateSecretRequest(

        @NotNull(message = "Project id is required")
        UUID projectId,

        @NotNull(message = "Environment id is required")
        UUID environmentId,

        @NotBlank(message = "Secret name is required")
        @Size(max = 150, message = "Secret name must not exceed 150 characters")
        String name,

        @NotNull(message = "Secret type is required")
        SecretType type,

        @NotBlank(message = "Secret value is required")
        String value
) {
}