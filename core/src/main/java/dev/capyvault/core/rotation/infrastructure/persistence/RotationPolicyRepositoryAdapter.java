package dev.capyvault.core.rotation.infrastructure.persistence;

import dev.capyvault.core.rotation.application.port.out.RotationPolicyPersistencePort;
import dev.capyvault.core.rotation.domain.RotationPolicy;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RotationPolicyRepositoryAdapter implements RotationPolicyPersistencePort {

    private final SpringDataRotationPolicyRepository repository;
    private final RotationPolicyMapper mapper = new RotationPolicyMapper();

    public RotationPolicyRepositoryAdapter(SpringDataRotationPolicyRepository repository) {
        this.repository = repository;
    }

    @Override
    public RotationPolicy save(RotationPolicy policy) {
        RotationPolicyJpaEntity entity = mapper.toEntity(policy);
        RotationPolicyJpaEntity savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<RotationPolicy> findBySecretId(UUID secretId) {
        return repository.findBySecretId(secretId)
                .map(mapper::toDomain);
    }
}