package dev.capyvault.projectservice.application.port.in;

import java.util.UUID;

public interface DeleteProjectUseCase {
    void delete(UUID projectUuid);
}
