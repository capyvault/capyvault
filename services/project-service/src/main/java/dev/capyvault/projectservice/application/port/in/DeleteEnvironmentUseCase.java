package dev.capyvault.projectservice.application.port.in;

import java.util.UUID;

public interface DeleteEnvironmentUseCase {
    void delete(UUID projectUuid, UUID environmentUuid);
}
