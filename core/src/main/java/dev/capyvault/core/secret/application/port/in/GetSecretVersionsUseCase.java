package dev.capyvault.core.secret.application.port.in;

import dev.capyvault.core.secret.application.query.SecretVersionResult;

import java.util.List;
import java.util.UUID;
public interface GetSecretVersionsUseCase { List<SecretVersionResult> list(UUID secretUuid); }
