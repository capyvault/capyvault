package dev.capyvault.identityservice.infrastructure.security;

import dev.capyvault.identityservice.application.port.out.UserRepository;
import dev.capyvault.identityservice.domain.User;
import dev.capyvault.identityservice.domain.UserStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService
        implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(
            String email
    ) throws UsernameNotFoundException {

        User user =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(
                                () ->
                                        new UsernameNotFoundException(
                                                "Invalid email or password."
                                        )
                        );

        return org.springframework.security.core.userdetails.User

                .withUsername(
                        user.getUuid().toString()
                )

                .password(
                        user.getPasswordHash()
                )

                .authorities("USER")

                .disabled(
                        user.getStatus()
                                != UserStatus.ACTIVE
                )

                .build();
    }
}
