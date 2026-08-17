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
                entity.getPrincipalId(),
                entity.getPrincipalType(),
                entity.getProjectId(),
                entity.getEnvironment(),
                entity.getScopeType(),
                entity.getRole(),
                entity.getEffect(),
                entity.getStatus(),
                entity.getValidFrom(),
                entity.getValidUntil(),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static AccessGrantJpaEntity toEntity(AccessGrant domain) {
        return new AccessGrantJpaEntity(
                domain.getId(),
                domain.getUuid(),
                domain.getPrincipalId(),
                domain.getPrincipalType(),
                domain.getProjectId(),
                domain.getEnvironment(),
                domain.getScopeType(),
                domain.getRole(),
                domain.getEffect(),
                domain.getStatus(),
                domain.getValidFrom(),
                domain.getValidUntil(),
                domain.getCreatedBy(),
                domain.getCreatedAt(),
                domain.getUpdatedAt()
        );
    }
}