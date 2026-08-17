package dev.capyvault.core.secret.application.port.in;

import dev.capyvault.core.secret.application.query.SecretResult;
import dev.capyvault.core.secret.web.SecretResponse;

import java.util.List;
import java.util.UUID;
public interface GetSecretUseCase {

    SecretResponse get(UUID secretId);
}