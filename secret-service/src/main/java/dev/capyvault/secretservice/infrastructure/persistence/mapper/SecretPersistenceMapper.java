package dev.capyvault.secretservice.infrastructure.persistence.mapper;

import dev.capyvault.secretservice.domain.secret.Secret;
import dev.capyvault.secretservice.domain.secret.SecretVersion;
import dev.capyvault.secretservice.infrastructure.persistence.entity.SecretJpaEntity;
import dev.capyvault.secretservice.infrastructure.persistence.entity.SecretVersionJpaEntity;

import java.util.ArrayList;

public class SecretPersistenceMapper {
    public static Secret toDomain(SecretJpaEntity entity) {
        var versions = entity.getVersions().stream().map(SecretPersistenceMapper::toDomainVersion).toList();
        Secret secret = new Secret();
        secret.restore(entity.getUuid(), entity.getProjectUuid(), entity.getEnvironmentUuid(), entity.getKey(),
                entity.getDescription(), entity.getStatus(), entity.getCreatedBy(), entity.getCreatedAt(), entity.getUpdatedAt(),
                new ArrayList<>(versions));
        return secret;
    }
    private static SecretVersion toDomainVersion(SecretVersionJpaEntity entity) {
        SecretVersion version = new SecretVersion();
        version.restore(entity.getUuid(), entity.getVersionNumber(), entity.getEncryptedValue(), entity.getEncryptionKeyId(),
                entity.getCreatedBy(), entity.getCreatedAt());
        return version;
    }
    public static SecretJpaEntity toEntity(Secret secret) {
        SecretJpaEntity entity = new SecretJpaEntity();
        entity.setUuid(secret.getUuid());
        entity.setProjectUuid(secret.getProjectUuid());
        entity.setEnvironmentUuid(secret.getEnvironmentUuid());
        entity.setKey(secret.getKey());
        entity.setDescription(secret.getDescription());
        entity.setStatus(secret.getStatus());
        entity.setCreatedBy(secret.getCreatedBy());
        entity.setCreatedAt(secret.getCreatedAt());
        entity.setUpdatedAt(secret.getUpdatedAt());
        secret.getVersions().forEach(v -> entity.addVersion(toEntityVersion(v)));
        return entity;
    }
    private static SecretVersionJpaEntity toEntityVersion(SecretVersion version) {
        SecretVersionJpaEntity entity = new SecretVersionJpaEntity();
        entity.setUuid(version.getUuid());
        entity.setVersionNumber(version.getVersionNumber());
        entity.setEncryptedValue(version.getEncryptedValue());
        entity.setEncryptionKeyId(version.getEncryptionKeyId());
        entity.setCreatedBy(version.getCreatedBy());
        entity.setCreatedAt(version.getCreatedAt());
        return entity;
    }
}
