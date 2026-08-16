package dev.capyvault.accesscontrolservice.application.command;

import dev.capyvault.accesscontrolservice.domain.AccessAction;

import java.util.UUID;

public record CheckAccessCommand(
        UUID projectId,
        UUID userId,
        String environment,
        AccessAction action
) {
}
