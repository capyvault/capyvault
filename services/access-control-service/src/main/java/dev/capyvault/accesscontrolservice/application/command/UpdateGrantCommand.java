package dev.capyvault.accesscontrolservice.application.command;

import dev.capyvault.accesscontrolservice.domain.GrantRole;
import dev.capyvault.accesscontrolservice.domain.GrantStatus;

import java.util.UUID;

public record UpdateGrantCommand(
        UUID grantId,
        GrantRole role,
        GrantStatus status
) {
}