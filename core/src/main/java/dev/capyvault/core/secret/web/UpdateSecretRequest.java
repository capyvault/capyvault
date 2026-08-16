package dev.capyvault.core.secret.web;

import jakarta.validation.constraints.Size;

public record UpdateSecretRequest(@Size(max = 500) String description) {}
