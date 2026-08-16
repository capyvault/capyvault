package dev.capyvault.secretservice.application.port.in;

import dev.capyvault.secretservice.application.command.UpdateSecretCommand;
import dev.capyvault.secretservice.application.query.SecretResult;
public interface UpdateSecretUseCase { SecretResult update(UpdateSecretCommand command); }
