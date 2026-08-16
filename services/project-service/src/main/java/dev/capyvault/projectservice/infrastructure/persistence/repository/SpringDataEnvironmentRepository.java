package dev.capyvault.projectservice.infrastructure.persistence.repository;

import dev.capyvault.projectservice.infrastructure.persistence.entity.EnvironmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataEnvironmentRepository extends JpaRepository<EnvironmentJpaEntity, Long> {
    Optional<EnvironmentJpaEntity> findByUuid(UUID uuid);
    Optional<EnvironmentJpaEntity> findByProjectUuidAndUuid(UUID projectUuid, UUID uuid);
    boolean existsByProjectUuidAndSlug(UUID projectUuid, String slug);
    boolean existsByProjectUuidAndSlugAndUuidNot(UUID projectUuid, String slug, UUID uuid);
    List<EnvironmentJpaEntity> findByProjectUuid(UUID projectUuid);
}
