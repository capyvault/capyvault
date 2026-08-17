package dev.capyvault.core.secret.application.port.in;

import dev.capyvault.core.secret.application.command.UpdateSecretValueCommand;
import dev.capyvault.core.secret.web.SecretResponse;

public interface UpdateSecretValueUseCase {

    SecretResponse updateValue(UpdateSecretValueCommand command);
}
