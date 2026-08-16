package dev.capyvault.projectservice.infrastructure.persistence.mapper;

import dev.capyvault.projectservice.domain.environment.Environment;
import dev.capyvault.projectservice.infrastructure.persistence.entity.EnvironmentJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentPersistenceMapper {

    public EnvironmentJpaEntity toEntity(Environment environment) {
        EnvironmentJpaEntity entity = new EnvironmentJpaEntity();
        entity.setUuid(environment.getUuid());
        entity.setProjectUuid(environment.getProjectUuid());
        entity.setName(environment.getName());
        entity.setSlug(environment.getSlug());
        entity.setStatus(environment.getStatus());
        entity.setCreatedAt(environment.getCreatedAt());
        entity.setUpdatedAt(environment.getUpdatedAt());
        return entity;
    }

    public void copyToEntity(Environment environment, EnvironmentJpaEntity entity) {
        entity.setName(environment.getName());
        entity.setSlug(environment.getSlug());
        entity.setStatus(environment.getStatus());
        entity.setUpdatedAt(environment.getUpdatedAt());
    }

    public Environment toDomain(EnvironmentJpaEntity entity) {
        return Environment.restore(
                entity.getUuid(),
                entity.getProjectUuid(),
                entity.getName(),
                entity.getSlug(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
