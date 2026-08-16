package dev.capyvault.secretservice.application.command;

import java.util.UUID;

public record CreateSecretCommand(UUID projectUuid, UUID environmentUuid, String key, String value, String description, UUID actorUuid) {}
