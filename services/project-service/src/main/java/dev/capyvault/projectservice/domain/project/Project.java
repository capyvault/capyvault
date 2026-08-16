package dev.capyvault.projectservice.domain.project;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class Project {

    private UUID uuid;
    private String name;
    private String slug;
    private String description;
    private ProjectStatus status;
    private UUID createdBy;
    private Instant createdAt;
    private Instant updatedAt;

    private Project() {
    }

    public static Project create(
            UUID uuid,
            String name,
            String slug,
            String description,
            UUID createdBy
    ) {
        Project project = new Project();
        project.uuid = uuid;
        project.name = name;
        project.slug = slug;
        project.description = description;
        project.status = ProjectStatus.ACTIVE;
        project.createdBy = createdBy;
        project.createdAt = Instant.now();
        project.updatedAt = Instant.now();
        return project;
    }

    public static Project restore(
            UUID uuid,
            String name,
            String slug,
            String description,
            ProjectStatus status,
            UUID createdBy,
            Instant createdAt,
            Instant updatedAt
    ) {
        Project project = new Project();
        project.uuid = uuid;
        project.name = name;
        project.slug = slug;
        project.description = description;
        project.status = status;
        project.createdBy = createdBy;
        project.createdAt = createdAt;
        project.updatedAt = updatedAt;
        return project;
    }

    public void update(String name, String slug, String description) {
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.updatedAt = Instant.now();
    }

    public void disable() {
        this.status = ProjectStatus.DISABLED;
        this.updatedAt = Instant.now();
    }

    public void activate() {
        this.status = ProjectStatus.ACTIVE;
        this.updatedAt = Instant.now();
    }

    public void markDeletionPending() {
        this.status = ProjectStatus.DELETION_PENDING;
        this.updatedAt = Instant.now();
    }

    public boolean isActive() {
        return ProjectStatus.ACTIVE.equals(status);
    }
}
