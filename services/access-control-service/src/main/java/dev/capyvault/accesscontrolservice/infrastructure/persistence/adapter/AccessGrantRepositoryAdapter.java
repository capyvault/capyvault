package dev.capyvault.accesscontrolservice.infrastructure.persistence.adapter;

import dev.capyvault.accesscontrolservice.application.port.out.AccessGrantRepository;
import dev.capyvault.accesscontrolservice.domain.AccessGrant;
import dev.capyvault.accesscontrolservice.domain.PrincipalType;
import dev.capyvault.accesscontrolservice.infrastructure.persistence.entity.AccessGrantJpaEntity;
import dev.capyvault.accesscontrolservice.infrastructure.persistence.mapper.AccessGrantMapper;
import dev.capyvault.accesscontrolservice.infrastructure.persistence.repository.SpringDataAccessGrantRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class AccessGrantRepositoryAdapter implements AccessGrantRepository {

    private final SpringDataAccessGrantRepository repository;

    public AccessGrantRepositoryAdapter(SpringDataAccessGrantRepository repository) {
        this.repository = repository;
    }

    @Override
    public AccessGrant save(AccessGrant grant) {
        AccessGrantJpaEntity saved = repository.save(AccessGrantMapper.toEntity(grant));
        return AccessGrantMapper.toDomain(saved);
    }

    @Override
    public Optional<AccessGrant> findByUuid(UUID uuid) {
        return repository.findByUuid(uuid)
                .map(AccessGrantMapper::toDomain);
    }

    @Override
    public List<AccessGrant> findByProjectId(UUID projectId) {
        return repository.findByProjectId(projectId)
                .stream()
                .map(AccessGrantMapper::toDomain)
                .toList();
    }

    @Override
    public List<AccessGrant> findByPrincipal(
            UUID principalId,
            PrincipalType principalType
    ) {
        return repository.findByPrincipalIdAndPrincipalType(principalId, principalType)
                .stream()
                .map(AccessGrantMapper::toDomain)
                .toList();
    }

    @Override
    public List<AccessGrant> findCandidateGrants(
            UUID principalId,
            PrincipalType principalType,
            UUID projectId
    ) {
        return repository.findByPrincipalIdAndPrincipalTypeAndProjectId(
                        principalId,
                        principalType,
                        projectId
                )
                .stream()
                .map(AccessGrantMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existsDuplicate(
            UUID principalId,
            PrincipalType principalType,
            UUID projectId,
            String environment
    ) {
        return repository.existsByPrincipalIdAndPrincipalTypeAndProjectIdAndEnvironment(
                principalId,
                principalType,
                projectId,
                environment
        );
    }

    @Override
    public void deleteByUuid(UUID uuid) {
        repository.deleteByUuid(uuid);
    }
}