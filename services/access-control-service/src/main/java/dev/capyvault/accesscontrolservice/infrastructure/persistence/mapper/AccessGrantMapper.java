package dev.capyvault.accesscontrolservice.infrastructure.persistence.mapper;

import dev.capyvault.accesscontrolservice.domain.AccessGrant;
import dev.capyvault.accesscontrolservice.infrastructure.persistence.entity.AccessGrantJpaEntity;

public class AccessGrantMapper {

    private AccessGrantMapper() {
    }

    public static AccessGrant toDomain(AccessGrantJpaEntity entity) {
        return new AccessGrant(
                entity.getId(),
                entity.getUuid(),
                entity.getProjectId(),
                entity.getUserId(),
                entity.getEnvironment(),
                entity.getRole(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static AccessGrantJpaEntity toEntity(AccessGrant domain) {
        return new AccessGrantJpaEntity(
                domain.getId(),
                domain.getUuid(),
                domain.getProjectId(),
                domain.getUserId(),
                domain.getEnvironment(),
                domain.getRole(),
                domain.getStatus(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
}