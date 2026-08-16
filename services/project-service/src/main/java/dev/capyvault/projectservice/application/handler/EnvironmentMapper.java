package dev.capyvault.projectservice.application.handler;

import dev.capyvault.projectservice.application.query.EnvironmentResult;
import dev.capyvault.projectservice.domain.environment.Environment;

final class EnvironmentMapper {

    private EnvironmentMapper() {
    }

    static EnvironmentResult toResult(Environment environment) {
        return new EnvironmentResult(
                environment.getUuid(),
                environment.getProjectUuid(),
                environment.getName(),
                environment.getSlug(),
                environment.getStatus(),
                environment.getCreatedAt(),
                environment.getUpdatedAt()
        );
    }
}
