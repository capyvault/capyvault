package dev.capyvault.secretservice.application.port.in;

import dev.capyvault.secretservice.application.command.RotateSecretCommand;
import dev.capyvault.secretservice.application.query.SecretResult;
public interface RotateSecretUseCase { SecretResult rotate(RotateSecretCommand command); }
