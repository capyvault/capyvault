package dev.capyvault.projectservice.application.handler;

import dev.capyvault.projectservice.application.command.UpdateProjectCommand;
import dev.capyvault.projectservice.application.port.in.UpdateProjectUseCase;
import dev.capyvault.projectservice.application.port.out.ProjectPersistencePort;
import dev.capyvault.projectservice.application.query.ProjectResult;
import dev.capyvault.projectservice.common.exception.ConflictException;
import dev.capyvault.projectservice.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateProjectHandler implements UpdateProjectUseCase {

    private final ProjectPersistencePort projectPersistencePort;

    @Override
    public ProjectResult update(UpdateProjectCommand command) {
        var project = projectPersistencePort.findByUuid(command.projectUuid())
                .orElseThrow(() -> new NotFoundException("PROJECT_NOT_FOUND", "Project not found"));

        if (projectPersistencePort.existsBySlugAndUuidNot(command.slug(), command.projectUuid())) {
            throw new ConflictException("PROJECT_SLUG_EXISTS", "Project slug already exists");
        }

        project.update(command.name(), command.slug(), command.description());
        return ProjectMapper.toResult(projectPersistencePort.save(project));
    }
}
