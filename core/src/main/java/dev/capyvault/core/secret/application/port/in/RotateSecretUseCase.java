package dev.capyvault.core.secret.application.port.in;

import dev.capyvault.core.secret.application.command.RotateSecretCommand;
import dev.capyvault.core.secret.application.query.SecretResult;

public interface RotateSecretUseCase { SecretResult rotate(RotateSecretCommand command); }
