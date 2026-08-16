package dev.capyvault.projectservice.infrastructure.persistence.mapper;

import dev.capyvault.projectservice.domain.project.Project;
import dev.capyvault.projectservice.infrastructure.persistence.entity.ProjectJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectPersistenceMapper {

    public ProjectJpaEntity toEntity(Project project) {
        ProjectJpaEntity entity = new ProjectJpaEntity();
        entity.setUuid(project.getUuid());
        entity.setName(project.getName());
        entity.setSlug(project.getSlug());
        entity.setDescription(project.getDescription());
        entity.setStatus(project.getStatus());
        entity.setCreatedBy(project.getCreatedBy());
        entity.setCreatedAt(project.getCreatedAt());
        entity.setUpdatedAt(project.getUpdatedAt());
        return entity;
    }

    public void copyToEntity(Project project, ProjectJpaEntity entity) {
        entity.setName(project.getName());
        entity.setSlug(project.getSlug());
        entity.setDescription(project.getDescription());
        entity.setStatus(project.getStatus());
        entity.setUpdatedAt(project.getUpdatedAt());
    }

    public Project toDomain(ProjectJpaEntity entity) {
        return Project.restore(
                entity.getUuid(),
                entity.getName(),
                entity.getSlug(),
                entity.getDescription(),
                entity.getStatus(),
                entity.getCreatedBy(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
