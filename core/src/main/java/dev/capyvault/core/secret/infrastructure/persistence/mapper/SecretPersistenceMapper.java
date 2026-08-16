package dev.capyvault.core.secret.infrastructure.persistence.mapper;

import dev.capyvault.core.secret.domain.*;
import dev.capyvault.core.secret.infrastructure.persistence.entity.SecretJpaEntity;
import dev.capyvault.core.secret.infrastructure.persistence.entity.SecretVersionJpaEntity;

public class SecretPersistenceMapper {

    public SecretJpaEntity toEntity(Secret secret) {
        SecretJpaEntity entity = new SecretJpaEntity(
                secret.id(),
                secret.projectId(),
                secret.environmentId(),
                secret.name(),
                secret.type().name(),
                secret.status().name(),
                secret.createdAt(),
                secret.updatedAt()
        );

        for (SecretVersion version : secret.versions()) {
            EncryptedSecretValue encrypted = version.encryptedValue();

            SecretVersionJpaEntity versionEntity = new SecretVersionJpaEntity(
                    version.id(),
                    version.versionNumber(),
                    encrypted.ciphertext(),
                    encrypted.keyId(),
                    encrypted.algorithm(),
                    encrypted.nonce(),
                    version.current(),
                    version.createdAt()
            );

            entity.addVersion(versionEntity);
        }

        return entity;
    }

    public Secret toDomain(SecretJpaEntity entity) {
        throw new UnsupportedOperationException("Implement when read use cases are added");
    }
}