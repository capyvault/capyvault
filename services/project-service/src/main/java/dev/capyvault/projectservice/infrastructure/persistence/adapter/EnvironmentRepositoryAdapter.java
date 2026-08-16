package dev.capyvault.projectservice.infrastructure.persistence.adapter;

import dev.capyvault.projectservice.application.port.out.EnvironmentPersistencePort;
import dev.capyvault.projectservice.domain.environment.Environment;
import dev.capyvault.projectservice.infrastructure.persistence.mapper.EnvironmentPersistenceMapper;
import dev.capyvault.projectservice.infrastructure.persistence.repository.SpringDataEnvironmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EnvironmentRepositoryAdapter implements EnvironmentPersistencePort {

    private final SpringDataEnvironmentRepository repository;
    private final EnvironmentPersistenceMapper mapper;

    @Override
    public Environment save(Environment environment) {
        var entity = repository.findByUuid(environment.getUuid())
                .map(existing -> {
                    mapper.copyToEntity(environment, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toEntity(environment));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Environment> findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).map(mapper::toDomain);
    }

    @Override
    public Optional<Environment> findByProjectUuidAndUuid(UUID projectUuid, UUID uuid) {
        return repository.findByProjectUuidAndUuid(projectUuid, uuid).map(mapper::toDomain);
    }

    @Override
    public boolean existsByProjectUuidAndSlug(UUID projectUuid, String slug) {
        return repository.existsByProjectUuidAndSlug(projectUuid, slug);
    }

    @Override
    public boolean existsByProjectUuidAndSlugAndUuidNot(UUID projectUuid, String slug, UUID uuid) {
        return repository.existsByProjectUuidAndSlugAndUuidNot(projectUuid, slug, uuid);
    }

    @Override
    public List<Environment> findByProjectUuid(UUID projectUuid) {
        return repository.findByProjectUuid(projectUuid)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
