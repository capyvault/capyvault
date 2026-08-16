package dev.capyvault.projectservice.application.command;

import java.util.UUID;

public record CreateProjectCommand(
        String name,
        String slug,
        String description,
        UUID actorUuid
) {
}
