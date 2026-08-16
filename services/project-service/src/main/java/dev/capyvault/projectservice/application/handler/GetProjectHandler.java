package dev.capyvault.projectservice.application.handler;

import dev.capyvault.projectservice.application.port.in.GetProjectUseCase;
import dev.capyvault.projectservice.application.port.out.ProjectPersistencePort;
import dev.capyvault.projectservice.application.query.ProjectResult;
import dev.capyvault.projectservice.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetProjectHandler implements GetProjectUseCase {

    private final ProjectPersistencePort projectPersistencePort;

    @Override
    public ProjectResult get(UUID projectUuid) {
        return projectPersistencePort.findByUuid(projectUuid)
                .map(ProjectMapper::toResult)
                .orElseThrow(() -> new NotFoundException("PROJECT_NOT_FOUND", "Project not found"));
    }

    @Override
    public List<ProjectResult> list() {
        return projectPersistencePort.findAll()
                .stream()
                .map(ProjectMapper::toResult)
                .toList();
    }
}
