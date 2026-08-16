package dev.capyvault.secretservice.application.command;

import java.util.UUID;

public record RotateSecretCommand(UUID secretUuid, String value, UUID actorUuid) {}
