package dev.capyvault.core.secret.application.port.in;

import dev.capyvault.core.secret.application.query.SecretValueResult;

import java.util.UUID;
public interface ReadSecretValueUseCase { SecretValueResult read(UUID secretUuid); }
