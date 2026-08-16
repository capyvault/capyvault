package dev.capyvault.projectservice.application.port.in;

import dev.capyvault.projectservice.application.command.UpdateEnvironmentCommand;
import dev.capyvault.projectservice.application.query.EnvironmentResult;

public interface UpdateEnvironmentUseCase {
    EnvironmentResult update(UpdateEnvironmentCommand command);
}
