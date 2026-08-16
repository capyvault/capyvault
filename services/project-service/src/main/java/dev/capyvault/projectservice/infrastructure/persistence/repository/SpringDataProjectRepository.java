package dev.capyvault.projectservice.infrastructure.persistence.repository;

import dev.capyvault.projectservice.infrastructure.persistence.entity.ProjectJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataProjectRepository extends JpaRepository<ProjectJpaEntity, Long> {
    Optional<ProjectJpaEntity> findByUuid(UUID uuid);
    Optional<ProjectJpaEntity> findBySlug(String slug);
    boolean existsBySlug(String slug);
    boolean existsBySlugAndUuidNot(String slug, UUID uuid);
}
