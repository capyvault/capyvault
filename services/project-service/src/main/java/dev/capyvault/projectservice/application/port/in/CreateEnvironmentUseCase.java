package dev.capyvault.projectservice.application.port.in;

import dev.capyvault.projectservice.application.command.CreateEnvironmentCommand;
import dev.capyvault.projectservice.application.query.EnvironmentResult;

public interface CreateEnvironmentUseCase {
    EnvironmentResult create(CreateEnvironmentCommand command);
}
