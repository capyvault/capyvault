package dev.capyvault.accesscontrolservice.api.request;

import dev.capyvault.accesscontrolservice.domain.GrantRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateGrantRequest(

        @NotNull(message = "Project ID is required")
        UUID projectId,

        @NotNull(message = "User ID is required")
        UUID userId,

        @NotBlank(message = "Environment is required")
        String environment,

        @NotNull(message = "Role is required")
        GrantRole role
) {
}