package dev.capyvault.accesscontrolservice.api.request;

import dev.capyvault.accesscontrolservice.domain.AccessAction;
import dev.capyvault.accesscontrolservice.domain.PrincipalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AccessCheckRequest(

        @NotNull(message = "Principal ID is required")
        UUID principalId,

        @NotNull(message = "Principal type is required")
        PrincipalType principalType,

        @NotNull(message = "Project ID is required")
        UUID projectId,

        @NotBlank(message = "Environment is required")
        String environment,

        @NotNull(message = "Action is required")
        AccessAction action
) {
}