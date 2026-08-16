package dev.capyvault.core.secret.application.port.in;

import dev.capyvault.core.secret.application.command.UpdateSecretCommand;
import dev.capyvault.core.secret.application.query.SecretResult;

public interface UpdateSecretUseCase { SecretResult update(UpdateSecretCommand command); }
