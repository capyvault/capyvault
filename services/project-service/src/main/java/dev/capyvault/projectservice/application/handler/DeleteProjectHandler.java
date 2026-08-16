package dev.capyvault.projectservice.application.handler;

import dev.capyvault.projectservice.application.port.in.DeleteProjectUseCase;
import dev.capyvault.projectservice.application.port.out.ProjectPersistencePort;
import dev.capyvault.projectservice.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteProjectHandler implements DeleteProjectUseCase {

    private final ProjectPersistencePort projectPersistencePort;

    @Override
    public void delete(UUID projectUuid) {
        var project = projectPersistencePort.findByUuid(projectUuid)
                .orElseThrow(() -> new NotFoundException("PROJECT_NOT_FOUND", "Project not found"));

        project.markDeletionPending();
        projectPersistencePort.save(project);
    }
}
