package dev.capyvault.projectservice.application.handler;

import dev.capyvault.projectservice.application.command.CreateProjectCommand;
import dev.capyvault.projectservice.application.port.in.CreateProjectUseCase;
import dev.capyvault.projectservice.application.port.out.ProjectPersistencePort;
import dev.capyvault.projectservice.application.query.ProjectResult;
import dev.capyvault.projectservice.common.exception.ConflictException;
import dev.capyvault.projectservice.domain.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateProjectHandler implements CreateProjectUseCase {

    private final ProjectPersistencePort projectPersistencePort;

    @Override
    public ProjectResult create(CreateProjectCommand command) {
        if (projectPersistencePort.existsBySlug(command.slug())) {
            throw new ConflictException("PROJECT_SLUG_EXISTS", "Project slug already exists");
        }

        Project project = Project.create(
                UUID.randomUUID(),
                command.name(),
                command.slug(),
                command.description(),
                command.actorUuid()
        );

        Project saved = projectPersistencePort.save(project);
        return ProjectMapper.toResult(saved);
    }
}
