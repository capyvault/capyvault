package dev.capyvault.accesscontrolservice.api.request;

import dev.capyvault.accesscontrolservice.domain.GrantRole;
import dev.capyvault.accesscontrolservice.domain.GrantStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateGrantRequest(

        @NotNull(message = "Role is required")
        GrantRole role,

        @NotNull(message = "Status is required")
        GrantStatus status
) {
}