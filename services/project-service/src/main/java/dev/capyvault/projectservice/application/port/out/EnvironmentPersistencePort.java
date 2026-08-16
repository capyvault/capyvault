package dev.capyvault.projectservice.application.port.out;

import dev.capyvault.projectservice.domain.environment.Environment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EnvironmentPersistencePort {
    Environment save(Environment environment);
    Optional<Environment> findByUuid(UUID uuid);
    Optional<Environment> findByProjectUuidAndUuid(UUID projectUuid, UUID uuid);
    boolean existsByProjectUuidAndSlug(UUID projectUuid, String slug);
    boolean existsByProjectUuidAndSlugAndUuidNot(UUID projectUuid, String slug, UUID uuid);
    List<Environment> findByProjectUuid(UUID projectUuid);
}
