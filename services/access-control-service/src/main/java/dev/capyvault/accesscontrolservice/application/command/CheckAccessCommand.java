package dev.capyvault.accesscontrolservice.application.command;

import dev.capyvault.accesscontrolservice.domain.AccessAction;
import dev.capyvault.accesscontrolservice.domain.PrincipalType;

import java.util.UUID;

public record CheckAccessCommand(
        UUID principalId,
        PrincipalType principalType,
        UUID projectId,
        String environment,
        AccessAction action
) {
}