package dev.capyvault.core.secret.application.query;

import java.util.UUID;

public record ProjectEnvironmentInfo(UUID projectUuid, String projectStatus, UUID environmentUuid,
                                     String environmentName, String environmentSlug, String environmentStatus,
                                     boolean available) {}
