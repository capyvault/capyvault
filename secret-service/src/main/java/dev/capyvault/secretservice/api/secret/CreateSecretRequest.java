package dev.capyvault.secretservice.api.secret;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record CreateSecretRequest(
        @NotNull UUID projectUuid,
        @NotNull UUID environmentUuid,
        @NotBlank @Size(max = 255) String key,
        @NotBlank String value,
        @Size(max = 500) String description,
        @NotNull UUID actorUuid
) {}
