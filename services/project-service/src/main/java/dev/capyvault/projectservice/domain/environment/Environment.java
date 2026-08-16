package dev.capyvault.projectservice.domain.environment;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class Environment {

    private UUID uuid;
    private UUID projectUuid;
    private String name;
    private String slug;
    private EnvironmentStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    private Environment() {
    }

    public static Environment create(
            UUID uuid,
            UUID projectUuid,
            String name,
            String slug
    ) {
        Environment environment = new Environment();
        environment.uuid = uuid;
        environment.projectUuid = projectUuid;
        environment.name = name;
        environment.slug = slug;
        environment.status = EnvironmentStatus.ACTIVE;
        environment.createdAt = Instant.now();
        environment.updatedAt = Instant.now();
        return environment;
    }

    public static Environment restore(
            UUID uuid,
            UUID projectUuid,
            String name,
            String slug,
            EnvironmentStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        Environment environment = new Environment();
        environment.uuid = uuid;
        environment.projectUuid = projectUuid;
        environment.name = name;
        environment.slug = slug;
        environment.status = status;
        environment.createdAt = createdAt;
        environment.updatedAt = updatedAt;
        return environment;
    }

    public void rename(String name, String slug) {
        this.name = name;
        this.slug = slug;
        this.updatedAt = Instant.now();
    }

    public void disable() {
        this.status = EnvironmentStatus.DISABLED;
        this.updatedAt = Instant.now();
    }

    public void activate() {
        this.status = EnvironmentStatus.ACTIVE;
        this.updatedAt = Instant.now();
    }

    public void delete() {
        this.status = EnvironmentStatus.DELETED;
        this.updatedAt = Instant.now();
    }
}
