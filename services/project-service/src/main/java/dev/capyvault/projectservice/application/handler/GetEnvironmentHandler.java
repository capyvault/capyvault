package dev.capyvault.projectservice.application.handler;

import dev.capyvault.projectservice.application.port.in.GetEnvironmentUseCase;
import dev.capyvault.projectservice.application.port.out.EnvironmentPersistencePort;
import dev.capyvault.projectservice.application.port.out.ProjectPersistencePort;
import dev.capyvault.projectservice.application.query.EnvironmentResult;
import dev.capyvault.projectservice.application.query.ProjectEnvironmentResult;
import dev.capyvault.projectservice.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetEnvironmentHandler implements GetEnvironmentUseCase {

    private final ProjectPersistencePort projectPersistencePort;
    private final EnvironmentPersistencePort environmentPersistencePort;

    @Override
    public EnvironmentResult get(UUID projectUuid, UUID environmentUuid) {
        return environmentPersistencePort.findByProjectUuidAndUuid(projectUuid, environmentUuid)
                .map(EnvironmentMapper::toResult)
                .orElseThrow(() -> new NotFoundException("ENVIRONMENT_NOT_FOUND", "Environment not found"));
    }

    @Override
    public List<EnvironmentResult> listByProject(UUID projectUuid) {
        projectPersistencePort.findByUuid(projectUuid)
                .orElseThrow(() -> new NotFoundException("PROJECT_NOT_FOUND", "Project not found"));

        return environmentPersistencePort.findByProjectUuid(projectUuid)
                .stream()
                .map(EnvironmentMapper::toResult)
                .toList();
    }

    @Override
    public ProjectEnvironmentResult getProjectEnvironment(UUID projectUuid, UUID environmentUuid) {
        var project = projectPersistencePort.findByUuid(projectUuid)
                .orElseThrow(() -> new NotFoundException("PROJECT_NOT_FOUND", "Project not found"));

        var environment = environmentPersistencePort.findByProjectUuidAndUuid(projectUuid, environmentUuid)
                .orElseThrow(() -> new NotFoundException("ENVIRONMENT_NOT_FOUND", "Environment not found"));

        return new ProjectEnvironmentResult(
                project.getUuid(),
                project.getStatus(),
                environment.getUuid(),
                environment.getName(),
                environment.getSlug(),
                environment.getStatus()
        );
    }
}
