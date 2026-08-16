package dev.capyvault.core.secret.application.port.in;

import dev.capyvault.core.secret.application.command.CreateSecretCommand;
import dev.capyvault.core.secret.web.SecretResponse;

public interface CreateSecretUseCase {

    SecretResponse create(CreateSecretCommand command);
}