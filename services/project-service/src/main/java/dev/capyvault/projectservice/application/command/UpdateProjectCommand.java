package dev.capyvault.projectservice.application.command;

import java.util.UUID;

public record UpdateProjectCommand(
        UUID projectUuid,
        String name,
        String slug,
        String description
) {
}
