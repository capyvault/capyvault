package dev.capyvault.core.secret.application.port.out;

import dev.capyvault.core.secret.domain.Secret;

public interface SecretPersistencePort {

    Secret save(Secret secret);
}