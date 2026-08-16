package dev.capyvault.core.secret.infrastructure.persistence.adapter;

import dev.capyvault.core.secret.application.port.out.SecretPersistencePort;
import dev.capyvault.core.secret.domain.Secret;
import dev.capyvault.core.secret.infrastructure.persistence.entity.SecretJpaEntity;
import dev.capyvault.core.secret.infrastructure.persistence.mapper.SecretPersistenceMapper;
import dev.capyvault.core.secret.infrastructure.persistence.repository.SpringDataSecretRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SecretRepositoryAdapter implements SecretPersistencePort {

    private final SpringDataSecretRepository repository;
    private final SecretPersistenceMapper mapper = new SecretPersistenceMapper();

    public SecretRepositoryAdapter(SpringDataSecretRepository repository) {
        this.repository = repository;
    }

    @Override
    public Secret save(Secret secret) {
        SecretJpaEntity entity = mapper.toEntity(secret);
        repository.save(entity);
        return secret;
    }
}