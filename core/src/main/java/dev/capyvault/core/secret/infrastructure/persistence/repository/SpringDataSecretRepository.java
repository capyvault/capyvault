package dev.capyvault.core.secret.infrastructure.persistence.repository;


import dev.capyvault.core.secret.infrastructure.persistence.entity.SecretJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface SpringDataSecretRepository extends JpaRepository<SecretJpaEntity, UUID> {

    boolean existsByProjectIdAndEnvironmentIdAndName(
            UUID projectId,
            UUID environmentId,
            String name
    );

    List<SecretJpaEntity> findByProjectIdAndEnvironmentIdAndStatusNot(
            UUID projectId,
            UUID environmentId,
            String status
    );
}