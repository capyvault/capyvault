package dev.capyvault.secretservice.infrastructure.persistence.adapter;

import dev.capyvault.secretservice.application.port.out.SecretPersistencePort;
import dev.capyvault.secretservice.domain.secret.Secret;
import dev.capyvault.secretservice.domain.secret.SecretStatus;
import dev.capyvault.secretservice.infrastructure.persistence.mapper.SecretPersistenceMapper;
import dev.capyvault.secretservice.infrastructure.persistence.repository.SpringDataSecretRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SecretRepositoryAdapter implements SecretPersistencePort {
    private final SpringDataSecretRepository repository;
    @Override
    public Secret save(Secret secret) {
        repository.findByUuid(secret.getUuid()).ifPresent(existing -> {
            repository.delete(existing);
            repository.flush();
        });
        var saved = repository.save(SecretPersistenceMapper.toEntity(secret));
        return SecretPersistenceMapper.toDomain(saved);
    }
    @Override
    public Optional<Secret> findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).filter(s -> s.getStatus() != SecretStatus.DELETED).map(SecretPersistenceMapper::toDomain);
    }
    @Override
    public List<Secret> findByProjectUuidAndEnvironmentUuid(UUID projectUuid, UUID environmentUuid) {
        return repository.findByProjectUuidAndEnvironmentUuidAndStatusNot(projectUuid, environmentUuid, SecretStatus.DELETED)
                .stream().map(SecretPersistenceMapper::toDomain).toList();
    }
    @Override
    public boolean exists(UUID projectUuid, UUID environmentUuid, String key) {
        return repository.existsByProjectUuidAndEnvironmentUuidAndKeyAndStatusNot(projectUuid, environmentUuid, key, SecretStatus.DELETED);
    }
}
