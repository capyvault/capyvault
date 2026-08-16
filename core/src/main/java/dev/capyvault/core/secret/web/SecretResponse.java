package dev.capyvault.core.secret.web;

import dev.capyvault.core.secret.domain.Secret;
import dev.capyvault.core.secret.domain.SecretStatus;
import dev.capyvault.core.secret.domain.SecretType;

import java.time.Instant;
import java.util.UUID;

public record SecretResponse(
        UUID id,
        UUID projectId,
        UUID environmentId,
        String name,
        SecretType type,
        SecretStatus status,
        int currentVersion,
        Instant createdAt,
        Instant updatedAt
) {

    public static SecretResponse from(Secret secret) {
        return new SecretResponse(
                secret.id(),
                secret.projectId(),
                secret.environmentId(),
                secret.name(),
                secret.type(),
                secret.status(),
                secret.currentVersion().versionNumber(),
                secret.createdAt(),
                secret.updatedAt()
        );
    }
}