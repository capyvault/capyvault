package dev.capyvault.core.secret.application.command;

import java.util.UUID;

public record UpdateSecretCommand(UUID secretUuid, String description) {}
