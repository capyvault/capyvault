package dev.capyvault.projectservice.application.handler;

import dev.capyvault.projectservice.application.command.CreateEnvironmentCommand;
import dev.capyvault.projectservice.application.port.in.CreateEnvironmentUseCase;
import dev.capyvault.projectservice.application.port.out.EnvironmentPersistencePort;
import dev.capyvault.projectservice.application.port.out.ProjectPersistencePort;
import dev.capyvault.projectservice.application.query.EnvironmentResult;
import dev.capyvault.projectservice.common.exception.BusinessException;
import dev.capyvault.projectservice.common.exception.ConflictException;
import dev.capyvault.projectservice.common.exception.NotFoundException;
import dev.capyvault.projectservice.domain.environment.Environment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateEnvironmentHandler implements CreateEnvironmentUseCase {

    private final ProjectPersistencePort projectPersistencePort;
    private final EnvironmentPersistencePort environmentPersistencePort;

    @Override
    public EnvironmentResult create(CreateEnvironmentCommand command) {
        var project = projectPersistencePort.findByUuid(command.projectUuid())
                .orElseThrow(() -> new NotFoundException("PROJECT_NOT_FOUND", "Project not found"));

        if (!project.isActive()) {
            throw new BusinessException("PROJECT_NOT_ACTIVE", "Project is not active");
        }

        if (environmentPersistencePort.existsByProjectUuidAndSlug(command.projectUuid(), command.slug())) {
            throw new ConflictException("ENVIRONMENT_SLUG_EXISTS", "Environment slug already exists in this project");
        }

        Environment environment = Environment.create(
                UUID.randomUUID(),
                command.projectUuid(),
                command.name(),
                command.slug()
        );

        return EnvironmentMapper.toResult(environmentPersistencePort.save(environment));
    }
}
