package dev.capyvault.projectservice.api.project;

import dev.capyvault.projectservice.application.query.ProjectResult;
import dev.capyvault.projectservice.domain.project.ProjectStatus;

import java.time.Instant;
import java.util.UUID;

public record ProjectResponse(
        UUID uuid,
        String name,
        String slug,
        String description,
        ProjectStatus status,
        UUID createdBy,
        Instant createdAt,
        Instant updatedAt
) {
    public static ProjectResponse from(ProjectResult result) {
        return new ProjectResponse(
                result.uuid(),
                result.name(),
                result.slug(),
                result.description(),
                result.status(),
                result.createdBy(),
                result.createdAt(),
                result.updatedAt()
        );
    }
}
