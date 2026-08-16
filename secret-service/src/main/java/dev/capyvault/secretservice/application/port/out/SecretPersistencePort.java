package dev.capyvault.secretservice.application.port.out;

import dev.capyvault.secretservice.domain.secret.Secret;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SecretPersistencePort {
    Secret save(Secret secret);
    Optional<Secret> findByUuid(UUID uuid);
    List<Secret> findByProjectUuidAndEnvironmentUuid(UUID projectUuid, UUID environmentUuid);
    boolean exists(UUID projectUuid, UUID environmentUuid, String key);
}
