package dev.capyvault.identityservice.infrastructure.persistence.repository;

import dev.capyvault.identityservice.infrastructure.persistence.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataUserRepository
        extends JpaRepository<UserJpaEntity, Long> {

    Optional<UserJpaEntity> findByUuid(
            UUID uuid
    );

    Optional<UserJpaEntity> findByEmailIgnoreCase(
            String email
    );

    Optional<UserJpaEntity> findByUsernameIgnoreCase(
            String username
    );

    boolean existsByEmailIgnoreCase(
            String email
    );

    boolean existsByUsernameIgnoreCase(
            String username
    );
}