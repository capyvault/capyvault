package dev.capyvault.secretservice.infrastructure.persistence.repository;

import dev.capyvault.secretservice.domain.secret.SecretStatus;
import dev.capyvault.secretservice.infrastructure.persistence.entity.SecretJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataSecretRepository extends JpaRepository<SecretJpaEntity, Long> {
    Optional<SecretJpaEntity> findByUuid(UUID uuid);
    List<SecretJpaEntity> findByProjectUuidAndEnvironmentUuidAndStatusNot(UUID projectUuid, UUID environmentUuid, SecretStatus status);
    boolean existsByProjectUuidAndEnvironmentUuidAndKeyAndStatusNot(UUID projectUuid, UUID environmentUuid, String key, SecretStatus status);
}
