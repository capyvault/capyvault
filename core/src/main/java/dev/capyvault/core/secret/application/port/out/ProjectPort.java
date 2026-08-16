package dev.capyvault.core.secret.application.port.out;

import dev.capyvault.core.secret.application.query.ProjectEnvironmentInfo;

import java.util.UUID;

public interface ProjectPort { ProjectEnvironmentInfo getEnvironment(UUID projectUuid, UUID environmentUuid); }
