package dev.capyvault.accesscontrolservice.application.command;

import dev.capyvault.accesscontrolservice.domain.GrantRole;

import java.util.UUID;

public record CreateGrantCommand(
        UUID projectId,
        UUID userId,
        String environment,
        GrantRole role
) {
}