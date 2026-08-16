package dev.capyvault.secretservice.application.port.in;

import dev.capyvault.secretservice.application.query.SecretValueResult;
import java.util.UUID;
public interface ReadSecretValueUseCase { SecretValueResult read(UUID secretUuid); }
