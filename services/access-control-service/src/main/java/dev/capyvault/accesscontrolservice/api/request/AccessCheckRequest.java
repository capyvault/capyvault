package dev.capyvault.accesscontrolservice.api.request;

import dev.capyvault.accesscontrolservice.domain.AccessAction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AccessCheckRequest(

        @NotNull(message = "Project ID is required")
        UUID projectId,

        @NotNull(message = "User ID is required")
        UUID userId,

        @NotBlank(message = "Environment is required")
        String environment,

        @NotNull(message = "Action is required")
        AccessAction action
) {
}