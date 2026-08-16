package dev.capyvault.projectservice.application.port.in;

import dev.capyvault.projectservice.application.query.ProjectResult;

import java.util.List;
import java.util.UUID;

public interface GetProjectUseCase {
    ProjectResult get(UUID projectUuid);
    List<ProjectResult> list();
}
