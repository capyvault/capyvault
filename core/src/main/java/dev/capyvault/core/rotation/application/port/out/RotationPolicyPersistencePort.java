package dev.capyvault.core.rotation.application.port.out;

import dev.capyvault.core.rotation.domain.RotationPolicy;

import java.util.Optional;
import java.util.UUID;

public interface RotationPolicyPersistencePort {

    RotationPolicy save(RotationPolicy policy);

    Optional<RotationPolicy> findBySecretId(UUID secretId);
}