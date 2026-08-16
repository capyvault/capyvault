package dev.capyvault.secretservice.api.secret;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record RotateSecretRequest(@NotBlank String value, @NotNull UUID actorUuid) {}
