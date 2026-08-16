package dev.capyvault.core.secret.application.command;

import dev.capyvault.core.secret.domain.SecretType;

import java.util.UUID;

public record CreateSecretCommand(
        UUID projectId,
        UUID environmentId,
        String name,
        SecretType type,
        String value
) {
}