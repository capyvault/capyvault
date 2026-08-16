package dev.capyvault.projectservice.infrastructure.persistence.entity;

import dev.capyvault.projectservice.domain.environment.EnvironmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(
        name = "environments",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_environment_project_slug", columnNames = {"project_uuid", "slug"})
        }
)
public class EnvironmentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Column(name = "project_uuid", nullable = false)
    private UUID projectUuid;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String slug;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private EnvironmentStatus status;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;
}
