package dev.capyvault.projectservice.application.command;

import java.util.UUID;

public record CreateEnvironmentCommand(
        UUID projectUuid,
        String name,
        String slug
) {
}
