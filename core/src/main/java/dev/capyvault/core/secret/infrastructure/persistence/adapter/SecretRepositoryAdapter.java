package dev.capyvault.core.secret.infrastructure.persistence.adapter;

import dev.capyvault.core.secret.application.port.out.SecretPersistencePort;
import dev.capyvault.core.secret.application.port.out.SecretQueryPort;
import dev.capyvault.core.secret.domain.Secret;
import dev.capyvault.core.secret.domain.SecretStatus;
import dev.capyvault.core.secret.infrastructure.persistence.entity.SecretJpaEntity;
import dev.capyvault.core.secret.infrastructure.persistence.mapper.SecretPersistenceMapper;
import dev.capyvault.core.secret.infrastructure.persistence.repository.SpringDataSecretRepository;
import dev.capyvault.core.secret.web.SecretResponse;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SecretRepositoryAdapter implements SecretPersistencePort, SecretQueryPort {

    private final SpringDataSecretRepository repository;
    private final SecretPersistenceMapper mapper = new SecretPersistenceMapper();

    public SecretRepositoryAdapter(SpringDataSecretRepository repository) {
        this.repository = repository;
    }

    @Override
    public Secret save(Secret secret) {
        SecretJpaEntity entity = mapper.toEntity(secret);
        SecretJpaEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Secret> findById(UUID id) {
        return repository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public boolean existsByProjectIdAndEnvironmentIdAndName(
            UUID projectId,
            UUID environmentId,
            String name
    ) {
        return repository.existsByProjectIdAndEnvironmentIdAndName(
                projectId,
                environmentId,
                name
        );
    }

    @Override
    public List<SecretResponse> search(UUID projectId, UUID environmentId) {
        return repository.findByProjectIdAndEnvironmentIdAndStatusNot(
                        projectId,
                        environmentId,
                        SecretStatus.DELETED.name()
                )
                .stream()
                .map(mapper::toDomain)
                .map(SecretResponse::from)
                .toList();
    }
}