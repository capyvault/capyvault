package dev.capyvault.identityservice.application.service;

import dev.capyvault.identityservice.api.request.LoginRequest;
import dev.capyvault.identityservice.api.response.LoginResponse;
import dev.capyvault.identityservice.application.port.out.UserRepository;
import dev.capyvault.identityservice.domain.User;
import dev.capyvault.identityservice.domain.UserStatus;
import dev.capyvault.identityservice.infrastructure.security.JwtService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional(readOnly = true)
    public LoginResponse login(
            LoginRequest request
    ) {

        String email =
                request.email()
                        .trim()
                        .toLowerCase();

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () ->
                                        new BadCredentialsException(
                                                "Invalid email or password."
                                        )
                        );

        if (user.getStatus() != UserStatus.ACTIVE) {

            throw new BadCredentialsException(
                    "User account is disabled."
            );
        }

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.password(),
                        user.getPasswordHash()
                );

        if (!passwordMatches) {

            throw new BadCredentialsException(
                    "Invalid email or password."
            );
        }

        String token =
                jwtService.generateToken(user);

        return new LoginResponse(
                token,
                "Bearer",
                jwtService.getExpirationSeconds()
        );
    }
}