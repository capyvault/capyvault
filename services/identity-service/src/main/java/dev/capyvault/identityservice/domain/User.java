package dev.capyvault.identityservice.domain;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;

    private UUID uuid;

    private String username;

    private String email;

    private String passwordHash;

    private UserStatus status;

    private Instant createdAt;

    private Instant updatedAt;
}
