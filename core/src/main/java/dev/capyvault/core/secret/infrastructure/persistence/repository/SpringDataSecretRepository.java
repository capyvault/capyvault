package dev.capyvault.core.secret.infrastructure.persistence.repository;

import dev.capyvault.core.secret.infrastructure.persistence.entity.SecretJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataSecretRepository extends JpaRepository<SecretJpaEntity, UUID> {
}