package dev.capyvault.projectservice.api.project;

import dev.capyvault.projectservice.application.command.CreateProjectCommand;
import dev.capyvault.projectservice.application.command.UpdateProjectCommand;
import dev.capyvault.projectservice.application.port.in.CreateProjectUseCase;
import dev.capyvault.projectservice.application.port.in.DeleteProjectUseCase;
import dev.capyvault.projectservice.application.port.in.GetProjectUseCase;
import dev.capyvault.projectservice.application.port.in.UpdateProjectUseCase;
import dev.capyvault.projectservice.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;
    private final UpdateProjectUseCase updateProjectUseCase;
    private final GetProjectUseCase getProjectUseCase;
    private final DeleteProjectUseCase deleteProjectUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<ProjectResponse>> create(
            @Valid @RequestBody CreateProjectRequest request
    ) {
        var result = createProjectUseCase.create(
                new CreateProjectCommand(
                        request.name(),
                        request.slug(),
                        request.description(),
                        request.actorUuid()
                )
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Project created successfully", ProjectResponse.from(result)));
    }

    @GetMapping
    public ApiResponse<List<ProjectResponse>> list() {
        var response = getProjectUseCase.list()
                .stream()
                .map(ProjectResponse::from)
                .toList();

        return ApiResponse.success("Projects retrieved successfully", response);
    }

    @GetMapping("/{projectUuid}")
    public ApiResponse<ProjectResponse> get(@PathVariable UUID projectUuid) {
        return ApiResponse.success(
                "Project retrieved successfully",
                ProjectResponse.from(getProjectUseCase.get(projectUuid))
        );
    }

    @PutMapping("/{projectUuid}")
    public ApiResponse<ProjectResponse> update(
            @PathVariable UUID projectUuid,
            @Valid @RequestBody UpdateProjectRequest request
    ) {
        var result = updateProjectUseCase.update(
                new UpdateProjectCommand(
                        projectUuid,
                        request.name(),
                        request.slug(),
                        request.description()
                )
        );

        return ApiResponse.success("Project updated successfully", ProjectResponse.from(result));
    }

    @DeleteMapping("/{projectUuid}")
    public ApiResponse<Void> delete(@PathVariable UUID projectUuid) {
        deleteProjectUseCase.delete(projectUuid);
        return ApiResponse.success("Project marked for deletion", null);
    }
}
