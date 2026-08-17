package dev.capyvault.accesscontrolservice.application.command;

import dev.capyvault.accesscontrolservice.domain.AccessEffect;
import dev.capyvault.accesscontrolservice.domain.GrantRole;
import dev.capyvault.accesscontrolservice.domain.GrantStatus;

import java.time.Instant;
import java.util.UUID;

public record UpdateGrantCommand(
        UUID grantId,
        GrantRole role,
        AccessEffect effect,
        GrantStatus status,
        Instant validFrom,
        Instant validUntil,
        UUID actorId
) {
}