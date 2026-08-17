package dev.capyvault.core.secret.infrastructure.persistence.mapper;
import dev.capyvault.core.secret.domain.*;
import dev.capyvault.core.secret.infrastructure.persistence.entity.SecretJpaEntity;
import dev.capyvault.core.secret.infrastructure.persistence.entity.SecretVersionJpaEntity;
import org.jspecify.annotations.NonNull;

import java.util.Comparator;
import java.util.List;

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
            SecretVersionJpaEntity versionEntity = getVersionEntity(version);

            entity.addVersion(versionEntity);
        }

        return entity;
    }

    private static @NonNull SecretVersionJpaEntity getVersionEntity(SecretVersion version) {
        EncryptedSecretValue encryptedValue = version.encryptedValue();

        return new SecretVersionJpaEntity(
                version.id(),
                version.versionNumber(),
                encryptedValue.ciphertext(),
                encryptedValue.keyId(),
                encryptedValue.algorithm(),
                encryptedValue.nonce(),
                version.current(),
                version.createdAt()
        );
    }

    public Secret toDomain(SecretJpaEntity entity) {
        List<SecretVersion> versions = entity.getVersions()
                .stream()
                .map(version -> SecretVersion.restore(
                        version.getId(),
                        version.getVersionNumber(),
                        new EncryptedSecretValue(
                                version.getCiphertext(),
                                version.getKeyId(),
                                version.getAlgorithm(),
                                version.getNonce()
                        ),
                        version.isCurrent(),
                        version.getCreatedAt()
                ))
                .sorted(Comparator.comparingInt(SecretVersion::versionNumber))
                .toList();

        return Secret.restore(
                entity.getId(),
                entity.getProjectId(),
                entity.getEnvironmentId(),
                entity.getName(),
                SecretType.valueOf(entity.getType()),
                SecretStatus.valueOf(entity.getStatus()),
                versions,
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}