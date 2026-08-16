package dev.capyvault.secretservice.application.port.in;

import dev.capyvault.secretservice.application.query.SecretResult;
import java.util.List;
import java.util.UUID;
public interface GetSecretUseCase { SecretResult get(UUID secretUuid); List<SecretResult> list(UUID projectUuid, UUID environmentUuid); }
