package dev.capyvault.secretservice.application.handler;

import dev.capyvault.secretservice.application.query.SecretResult;
import dev.capyvault.secretservice.application.query.SecretVersionResult;
import dev.capyvault.secretservice.domain.secret.Secret;
import dev.capyvault.secretservice.domain.secret.SecretVersion;

public class SecretMapper {
    public static SecretResult toResult(Secret secret) {
        int latest = secret.getVersions().stream().map(SecretVersion::getVersionNumber).max(Integer::compareTo).orElse(0);
        return new SecretResult(secret.getUuid(), secret.getProjectUuid(), secret.getEnvironmentUuid(), secret.getKey(),
                secret.getDescription(), secret.getStatus(), latest, secret.getCreatedAt(), secret.getUpdatedAt());
    }
    public static SecretVersionResult toVersionResult(SecretVersion version) {
        return new SecretVersionResult(version.getUuid(), version.getVersionNumber(), version.getEncryptionKeyId(),
                version.getCreatedBy(), version.getCreatedAt());
    }
}
