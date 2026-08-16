package dev.capyvault.secretservice.infrastructure.client.project;

import java.util.UUID;

public record ProjectEnvironmentResponse(UUID projectUuid, String projectStatus, UUID environmentUuid,
                                         String environmentName, String environmentSlug, String environmentStatus,
                                         boolean available) {}
