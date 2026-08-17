package dev.capyvault.core.secret.application.port.out;

import dev.capyvault.core.secret.domain.Secret;

import java.util.Optional;
import java.util.UUID;

public interface SecretPersistencePort {

    Secret save(Secret secret);

    Optional<Secret> findById(UUID id);

    boolean existsByProjectIdAndEnvironmentIdAndName(
            UUID projectId,
            UUID environmentId,
            String name
    );
}