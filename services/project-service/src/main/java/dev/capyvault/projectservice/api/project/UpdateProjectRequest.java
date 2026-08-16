package dev.capyvault.projectservice.api.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateProjectRequest(
        @NotBlank(message = "Project name is required")
        @Size(max = 150, message = "Project name must not exceed 150 characters")
        String name,

        @NotBlank(message = "Project slug is required")
        @Size(max = 150, message = "Project slug must not exceed 150 characters")
        String slug,

        @Size(max = 500, message = "Description must not exceed 500 characters")
        String description
) {
}
