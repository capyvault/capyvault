package dev.capyvault.identityservice.infrastructure.persistence.entity;

import dev.capyvault.identityservice.domain.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(name = "users")
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(
            nullable = false,
            unique = true,
            updatable = false
    )
    private UUID uuid;

    @Setter
    @Column(
            nullable = false,
            unique = true,
            length = 100
    )
    private String username;

    @Setter
    @Column(
            nullable = false,
            unique = true,
            length = 255
    )
    private String email;

    @Setter
    @Column(
            name = "password_hash",
            nullable = false,
            length = 255
    )
    private String passwordHash;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 30
    )
    private UserStatus status;

    @Column(
            name = "created_at",
            nullable = false,
            updatable = false
    )
    private Instant createdAt;

    @Column(
            name = "updated_at",
            nullable = false
    )
    private Instant updatedAt;

    @PrePersist
    void prePersist() {

        Instant now = Instant.now();

        if (uuid == null) {
            uuid = UUID.randomUUID();
        }

        if (status == null) {
            status = UserStatus.ACTIVE;
        }

        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = Instant.now();
    }

}