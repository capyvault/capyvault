package dev.capyvault.projectservice.application.port.in;

import dev.capyvault.projectservice.application.command.CreateProjectCommand;
import dev.capyvault.projectservice.application.query.ProjectResult;

public interface CreateProjectUseCase {
    ProjectResult create(CreateProjectCommand command);
}
