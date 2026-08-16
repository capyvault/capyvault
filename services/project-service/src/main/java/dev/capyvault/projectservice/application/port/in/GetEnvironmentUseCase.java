package dev.capyvault.projectservice.application.port.in;

import dev.capyvault.projectservice.application.query.EnvironmentResult;
import dev.capyvault.projectservice.application.query.ProjectEnvironmentResult;

import java.util.List;
import java.util.UUID;

public interface GetEnvironmentUseCase {
    EnvironmentResult get(UUID projectUuid, UUID environmentUuid);
    List<EnvironmentResult> listByProject(UUID projectUuid);
    ProjectEnvironmentResult getProjectEnvironment(UUID projectUuid, UUID environmentUuid);
}
