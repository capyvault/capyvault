package dev.capyvault.projectservice.application.handler;

import dev.capyvault.projectservice.application.query.ProjectResult;
import dev.capyvault.projectservice.domain.project.Project;

final class ProjectMapper {

    private ProjectMapper() {
    }

    static ProjectResult toResult(Project project) {
        return new ProjectResult(
                project.getUuid(),
                project.getName(),
                project.getSlug(),
                project.getDescription(),
                project.getStatus(),
                project.getCreatedBy(),
                project.getCreatedAt(),
                project.getUpdatedAt()
        );
    }
}
