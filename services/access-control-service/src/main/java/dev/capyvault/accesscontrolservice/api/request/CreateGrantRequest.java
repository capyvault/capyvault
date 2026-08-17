package dev.capyvault.accesscontrolservice.api.request;

import dev.capyvault.accesscontrolservice.domain.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record CreateGrantRequest(

        @NotNull(message = "Principal ID is required")
        UUID principalId,

        @NotNull(message = "Principal type is required")
        PrincipalType principalType,

        @NotNull(message = "Project ID is required")
        UUID projectId,

        String environment,

        @NotNull(message = "Scope type is required")
        AccessScopeType scopeType,

        @NotNull(message = "Role is required")
        GrantRole role,

        @NotNull(message = "Effect is required")
        AccessEffect effect,

        Instant validFrom,

        Instant validUntil,

        @NotNull(message = "Created by is required")
        UUID createdBy
) {
}