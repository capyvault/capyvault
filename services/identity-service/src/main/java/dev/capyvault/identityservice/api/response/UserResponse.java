package dev.capyvault.identityservice.api.response;


import dev.capyvault.identityservice.domain.User;
import dev.capyvault.identityservice.domain.UserStatus;

import java.time.Instant;
import java.util.UUID;

public record UserResponse(

        UUID uuid,

        String username,

        String email,

        UserStatus status,

        Instant createdAt,

        Instant updatedAt

) {

    public static UserResponse from(
            User user
    ) {

        return new UserResponse(
                user.getUuid(),
                user.getUsername(),
                user.getEmail(),
                user.getStatus(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
