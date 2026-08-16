package dev.capyvault.secretservice.application.port.in;

import dev.capyvault.secretservice.application.query.SecretVersionResult;
import java.util.List;
import java.util.UUID;
public interface GetSecretVersionsUseCase { List<SecretVersionResult> list(UUID secretUuid); }
