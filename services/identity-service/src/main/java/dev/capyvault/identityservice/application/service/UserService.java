package dev.capyvault.identityservice.application.service;

import dev.capyvault.identityservice.api.request.CreateUserRequest;
import dev.capyvault.identityservice.api.response.UserResponse;
import dev.capyvault.identityservice.application.exception.EmailAlreadyExistsException;
import dev.capyvault.identityservice.application.exception.UserNotFoundException;
import dev.capyvault.identityservice.application.port.out.UserRepository;
import dev.capyvault.identityservice.domain.User;
import dev.capyvault.identityservice.domain.UserStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse create(
            CreateUserRequest request
    ) {

        String email =
                request.email()
                        .trim()
                        .toLowerCase();

        String username =
                request.username()
                        .trim();

        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }

        if (userRepository.existsByUsername(username)) {
            throw new IllegalArgumentException(
                    "Username already exists."
            );
        }

        String passwordHash =
                passwordEncoder.encode(
                        request.password()
                );

        User user =
                new User(
                        null,
                        UUID.randomUUID(),
                        username,
                        email,
                        passwordHash,
                        UserStatus.ACTIVE,
                        null,
                        null
                );

        User saved =
                userRepository.save(user);

        return UserResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public UserResponse findByUuid(
            UUID uuid
    ) {

        User user =
                userRepository
                        .findByUuid(uuid)
                        .orElseThrow(
                                UserNotFoundException::new
                        );

        return UserResponse.from(user);
    }
}
