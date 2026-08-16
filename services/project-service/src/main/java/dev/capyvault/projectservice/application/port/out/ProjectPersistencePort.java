package dev.capyvault.projectservice.application.port.out;

import dev.capyvault.projectservice.domain.project.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectPersistencePort {
    Project save(Project project);
    Optional<Project> findByUuid(UUID uuid);
    Optional<Project> findBySlug(String slug);
    boolean existsBySlug(String slug);
    boolean existsBySlugAndUuidNot(String slug, UUID uuid);
    List<Project> findAll();
}
