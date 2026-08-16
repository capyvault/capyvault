package dev.capyvault.projectservice.api.internal;

import dev.capyvault.projectservice.application.query.ProjectEnvironmentResult;
import dev.capyvault.projectservice.domain.environment.EnvironmentStatus;
import dev.capyvault.projectservice.domain.project.ProjectStatus;

import java.util.UUID;

public record ProjectEnvironmentValidationResponse(
        UUID projectUuid,
        ProjectStatus projectStatus,
        UUID environmentUuid,
        String environmentName,
        String environmentSlug,
        EnvironmentStatus environmentStatus,
        boolean available
) {
    public static ProjectEnvironmentValidationResponse from(ProjectEnvironmentResult result) {
        boolean available = ProjectStatus.ACTIVE.equals(result.projectStatus())
                && EnvironmentStatus.ACTIVE.equals(result.environmentStatus());

        return new ProjectEnvironmentValidationResponse(
                result.projectUuid(),
                result.projectStatus(),
                result.environmentUuid(),
                result.environmentName(),
                result.environmentSlug(),
                result.environmentStatus(),
                available
        );
    }
}
