package dev.capyvault.projectservice.application.query;

import dev.capyvault.projectservice.domain.project.ProjectStatus;

import java.time.Instant;
import java.util.UUID;

public record ProjectResult(
        UUID uuid,
        String name,
        String slug,
        String description,
        ProjectStatus status,
        UUID createdBy,
        Instant createdAt,
        Instant updatedAt
) {
}
