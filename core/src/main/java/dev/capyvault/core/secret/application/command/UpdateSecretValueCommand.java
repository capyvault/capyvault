package dev.capyvault.core.secret.application.command;


import java.util.UUID;

public record UpdateSecretValueCommand(
        UUID secretId,
        String newValue
) {
}
