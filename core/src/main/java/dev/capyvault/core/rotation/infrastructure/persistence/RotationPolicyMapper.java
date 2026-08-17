package dev.capyvault.core.rotation.infrastructure.persistence;

import dev.capyvault.core.rotation.domain.RotationPolicy;
import dev.capyvault.core.rotation.domain.RotationStatus;
import dev.capyvault.core.rotation.domain.RotationStrategy;

public class RotationPolicyMapper {

    public RotationPolicyJpaEntity toEntity(RotationPolicy policy) {
        return new RotationPolicyJpaEntity(
                policy.id(),
                policy.secretId(),
                policy.intervalDays(),
                policy.strategy().name(),
                policy.status().name(),
                policy.lastRotatedAt(),
                policy.nextRotationAt(),
                policy.createdAt(),
                policy.updatedAt()
        );
    }

    public RotationPolicy toDomain(RotationPolicyJpaEntity entity) {
        return RotationPolicy.restore(
                entity.getId(),
                entity.getSecretId(),
                entity.getIntervalDays(),
                RotationStrategy.valueOf(entity.getStrategy()),
                RotationStatus.valueOf(entity.getStatus()),
                entity.getLastRotatedAt(),
                entity.getNextRotationAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}