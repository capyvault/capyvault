package dev.capyvault.core.secret.application.port.in;

import dev.capyvault.core.secret.web.SecretValueResponse;

import java.util.UUID;

public interface GetSecretValueUseCase {

    SecretValueResponse getValue(UUID secretId);
}