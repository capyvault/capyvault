package dev.capyvault.core.secret.application.port.in;

import java.util.UUID;
public interface DeleteSecretUseCase { void delete(UUID secretUuid); }
