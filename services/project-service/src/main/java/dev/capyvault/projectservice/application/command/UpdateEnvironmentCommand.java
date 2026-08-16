package dev.capyvault.projectservice.application.command;

import java.util.UUID;

public record UpdateEnvironmentCommand(
        UUID projectUuid,
        UUID environmentUuid,
        String name,
        String slug
) {
}
