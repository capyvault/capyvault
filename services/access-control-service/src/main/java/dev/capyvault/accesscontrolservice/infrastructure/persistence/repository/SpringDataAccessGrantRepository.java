package dev.capyvault.accesscontrolservice.infrastructure.persistence.repository;

import dev.capyvault.accesscontrolservice.infrastructure.persistence.entity.AccessGrantJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataAccessGrantRepository extends JpaRepository<AccessGrantJpaEntity, Long> {

    Optional<AccessGrantJpaEntity> findByUuid(UUID uuid);

    Optional<AccessGrantJpaEntity> findByProjectIdAndUserIdAndEnvironment(
            UUID projectId,
            UUID userId,
            String environment
    );

    List<AccessGrantJpaEntity> findByProjectId(UUID projectId);

    boolean existsByProjectIdAndUserIdAndEnvironment(
            UUID projectId,
            UUID userId,
            String environment
    );

    void deleteByUuid(UUID uuid);
}