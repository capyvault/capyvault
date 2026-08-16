package dev.capyvault.secretservice.application.port.out;

import dev.capyvault.secretservice.application.query.ProjectEnvironmentInfo;
import java.util.UUID;

public interface ProjectPort { ProjectEnvironmentInfo getEnvironment(UUID projectUuid, UUID environmentUuid); }
