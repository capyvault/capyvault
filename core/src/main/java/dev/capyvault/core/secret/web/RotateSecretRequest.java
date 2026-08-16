package dev.capyvault.core.secret.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record RotateSecretRequest(@NotBlank String value, @NotNull UUID actorUuid) {}
