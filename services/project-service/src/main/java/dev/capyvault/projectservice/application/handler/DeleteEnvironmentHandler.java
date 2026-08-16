package dev.capyvault.projectservice.application.handler;

import dev.capyvault.projectservice.application.port.in.DeleteEnvironmentUseCase;
import dev.capyvault.projectservice.application.port.out.EnvironmentPersistencePort;
import dev.capyvault.projectservice.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteEnvironmentHandler implements DeleteEnvironmentUseCase {

    private final EnvironmentPersistencePort environmentPersistencePort;

    @Override
    public void delete(UUID projectUuid, UUID environmentUuid) {
        var environment = environmentPersistencePort.findByProjectUuidAndUuid(projectUuid, environmentUuid)
                .orElseThrow(() -> new NotFoundException("ENVIRONMENT_NOT_FOUND", "Environment not found"));

        environment.delete();
        environmentPersistencePort.save(environment);
    }
}
