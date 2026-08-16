package dev.capyvault.identityservice.infrastructure.persistence.adapter;

import dev.capyvault.identityservice.application.port.out.UserRepository;
import dev.capyvault.identityservice.domain.User;
import dev.capyvault.identityservice.infrastructure.persistence.entity.UserJpaEntity;
import dev.capyvault.identityservice.infrastructure.persistence.repository.SpringDataUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final SpringDataUserRepository repository;

    public UserRepositoryAdapter(
            SpringDataUserRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public User save(
            User user
    ) {

        UserJpaEntity entity = new UserJpaEntity();

        entity.setUuid(user.getUuid());
        entity.setUsername(user.getUsername());
        entity.setEmail(user.getEmail());
        entity.setPasswordHash(user.getPasswordHash());
        entity.setStatus(user.getStatus());

        UserJpaEntity saved =
                repository.save(entity);

        return toDomain(saved);
    }

    @Override
    public Optional<User> findByUuid(
            UUID uuid
    ) {

        return repository
                .findByUuid(uuid)
                .map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(
            String email
    ) {

        return repository
                .findByEmailIgnoreCase(email)
                .map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(
            String email
    ) {

        return repository
                .existsByEmailIgnoreCase(email);
    }

    @Override
    public boolean existsByUsername(
            String username
    ) {

        return repository
                .existsByUsernameIgnoreCase(username);
    }

    private User toDomain(
            UserJpaEntity entity
    ) {

        return new User(
                entity.getId(),
                entity.getUuid(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}