package dev.capyvault.projectservice.application.query;

import dev.capyvault.projectservice.domain.environment.EnvironmentStatus;
import dev.capyvault.projectservice.domain.project.ProjectStatus;

import java.util.UUID;

public record ProjectEnvironmentResult(
        UUID projectUuid,
        ProjectStatus projectStatus,
        UUID environmentUuid,
        String environmentName,
        String environmentSlug,
        EnvironmentStatus environmentStatus
) {
}
