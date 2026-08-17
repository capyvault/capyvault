package dev.capyvault.core.secret.web;

import dev.capyvault.core.secret.application.query.SecretValueResult;

import java.util.UUID;

public record SecretValueResponse(
        UUID id,
        String name,
        int version,
        String value
) {
}