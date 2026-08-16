package dev.capyvault.secretservice.application.port.in;

import dev.capyvault.secretservice.application.command.CreateSecretCommand;
import dev.capyvault.secretservice.application.query.SecretResult;
public interface CreateSecretUseCase { SecretResult create(CreateSecretCommand command); }
