package dev.capyvault.projectservice.application.handler;

import dev.capyvault.projectservice.application.command.UpdateEnvironmentCommand;
import dev.capyvault.projectservice.application.port.in.UpdateEnvironmentUseCase;
import dev.capyvault.projectservice.application.port.out.EnvironmentPersistencePort;
import dev.capyvault.projectservice.application.query.EnvironmentResult;
import dev.capyvault.projectservice.common.exception.ConflictException;
import dev.capyvault.projectservice.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateEnvironmentHandler implements UpdateEnvironmentUseCase {

    private final EnvironmentPersistencePort environmentPersistencePort;

    @Override
    public EnvironmentResult update(UpdateEnvironmentCommand command) {
        var environment = environmentPersistencePort
                .findByProjectUuidAndUuid(command.projectUuid(), command.environmentUuid())
                .orElseThrow(() -> new NotFoundException("ENVIRONMENT_NOT_FOUND", "Environment not found"));

        if (environmentPersistencePort.existsByProjectUuidAndSlugAndUuidNot(
                command.projectUuid(),
                command.slug(),
                command.environmentUuid()
        )) {
            throw new ConflictException("ENVIRONMENT_SLUG_EXISTS", "Environment slug already exists in this project");
        }

        environment.rename(command.name(), command.slug());
        return EnvironmentMapper.toResult(environmentPersistencePort.save(environment));
    }
}
