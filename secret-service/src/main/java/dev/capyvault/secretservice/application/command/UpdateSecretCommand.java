package dev.capyvault.secretservice.application.command;

import java.util.UUID;

public record UpdateSecretCommand(UUID secretUuid, String description) {}
