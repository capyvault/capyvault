package dev.capyvault.secretservice.api.secret;

import jakarta.validation.constraints.Size;

public record UpdateSecretRequest(@Size(max = 500) String description) {}
