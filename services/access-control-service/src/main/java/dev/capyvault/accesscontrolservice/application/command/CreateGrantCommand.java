package dev.capyvault.accesscontrolservice.application.command;

import dev.capyvault.accesscontrolservice.domain.*;

import java.time.Instant;
import java.util.UUID;

public record CreateGrantCommand(
        UUID principalId,
        PrincipalType principalType,
        UUID projectId,
        String environment,
        AccessScopeType scopeType,
        GrantRole role,
        AccessEffect effect,
        Instant validFrom,
        Instant validUntil,
        UUID createdBy
) {
}