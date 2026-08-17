package dev.capyvault.core.secret.application.port.in;

import dev.capyvault.core.secret.application.command.RotateSecretCommand;
import dev.capyvault.core.secret.application.query.SecretResult;
import dev.capyvault.core.secret.web.SecretResponse;

public interface RotateSecretUseCase {
    RotateSecretResult rotate(RotateSecretRequest command);
}