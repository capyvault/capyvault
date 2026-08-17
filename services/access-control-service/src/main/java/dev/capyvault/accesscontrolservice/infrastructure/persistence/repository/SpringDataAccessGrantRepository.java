package dev.capyvault.accesscontrolservice.infrastructure.persistence.repository;

import dev.capyvault.accesscontrolservice.domain.PrincipalType;
import dev.capyvault.accesscontrolservice.infrastructure.persistence.entity.AccessGrantJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataAccessGrantRepository extends JpaRepository<AccessGrantJpaEntity, Long> {

    Optional<AccessGrantJpaEntity> findByUuid(UUID uuid);

    List<AccessGrantJpaEntity> findByProjectId(UUID projectId);

    List<AccessGrantJpaEntity> findByPrincipalIdAndPrincipalType(
            UUID principalId,
            PrincipalType principalType
    );

    List<AccessGrantJpaEntity> findByPrincipalIdAndPrincipalTypeAndProjectId(
            UUID principalId,
            PrincipalType principalType,
            UUID projectId
    );

    boolean existsByPrincipalIdAndPrincipalTypeAndProjectIdAndEnvironment(
            UUID principalId,
            PrincipalType principalType,
            UUID projectId,
            String environment
    );

    void deleteByUuid(UUID uuid);
}