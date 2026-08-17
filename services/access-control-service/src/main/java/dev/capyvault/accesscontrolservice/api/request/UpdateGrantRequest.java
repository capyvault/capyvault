package dev.capyvault.accesscontrolservice.api.request;

import dev.capyvault.accesscontrolservice.domain.AccessEffect;
import dev.capyvault.accesscontrolservice.domain.GrantRole;
import dev.capyvault.accesscontrolservice.domain.GrantStatus;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record UpdateGrantRequest(

        @NotNull(message = "Role is required")
        GrantRole role,

        @NotNull(message = "Effect is required")
        AccessEffect effect,

        @NotNull(message = "Status is required")
        GrantStatus status,

        Instant validFrom,

        Instant validUntil,

        @NotNull(message = "Actor ID is required")
        UUID actorId
) {
}