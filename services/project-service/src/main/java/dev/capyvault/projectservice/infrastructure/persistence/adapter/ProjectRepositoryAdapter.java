package dev.capyvault.projectservice.infrastructure.persistence.adapter;

import dev.capyvault.projectservice.application.port.out.ProjectPersistencePort;
import dev.capyvault.projectservice.domain.project.Project;
import dev.capyvault.projectservice.infrastructure.persistence.mapper.ProjectPersistenceMapper;
import dev.capyvault.projectservice.infrastructure.persistence.repository.SpringDataProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectRepositoryAdapter implements ProjectPersistencePort {

    private final SpringDataProjectRepository repository;
    private final ProjectPersistenceMapper mapper;

    @Override
    public Project save(Project project) {
        var entity = repository.findByUuid(project.getUuid())
                .map(existing -> {
                    mapper.copyToEntity(project, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toEntity(project));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public Optional<Project> findByUuid(UUID uuid) {
        return repository.findByUuid(uuid).map(mapper::toDomain);
    }

    @Override
    public Optional<Project> findBySlug(String slug) {
        return repository.findBySlug(slug).map(mapper::toDomain);
    }

    @Override
    public boolean existsBySlug(String slug) {
        return repository.existsBySlug(slug);
    }

    @Override
    public boolean existsBySlugAndUuidNot(String slug, UUID uuid) {
        return repository.existsBySlugAndUuidNot(slug, uuid);
    }

    @Override
    public List<Project> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
