package dev.capyvault.core.secret.web;

import jakarta.validation.constraints.NotBlank;

public record RotateSecretRequest(

        @NotBlank(message = "Rotated value is required")
        String value
) {
}