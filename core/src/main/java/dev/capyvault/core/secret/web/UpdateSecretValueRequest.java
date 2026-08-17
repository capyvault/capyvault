package dev.capyvault.core.secret.web;

import jakarta.validation.constraints.NotBlank;

public record UpdateSecretValueRequest(

        @NotBlank(message = "Secret value is required")
        String value
) {
}
