package dev.capyvault.secretservice.application.port.in;

import java.util.UUID;
public interface DeleteSecretUseCase { void delete(UUID secretUuid); }
