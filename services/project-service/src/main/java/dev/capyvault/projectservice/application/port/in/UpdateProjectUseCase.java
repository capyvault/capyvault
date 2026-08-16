package dev.capyvault.projectservice.application.port.in;

import dev.capyvault.projectservice.application.command.UpdateProjectCommand;
import dev.capyvault.projectservice.application.query.ProjectResult;

public interface UpdateProjectUseCase {
    ProjectResult update(UpdateProjectCommand command);
}
