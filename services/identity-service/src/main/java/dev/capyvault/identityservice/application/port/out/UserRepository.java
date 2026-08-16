package dev.capyvault.identityservice.application.port.out;

import dev.capyvault.identityservice.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(
            User user
    );

    Optional<User> findByUuid(
            UUID uuid
    );

    Optional<User> findByEmail(
            String email
    );

    boolean existsByEmail(
            String email
    );

    boolean existsByUsername(
            String username
    );
}
