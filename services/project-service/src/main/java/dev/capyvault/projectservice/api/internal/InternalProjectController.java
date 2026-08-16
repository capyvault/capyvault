package dev.capyvault.projectservice.api.internal;

import dev.capyvault.projectservice.application.port.in.GetEnvironmentUseCase;
import dev.capyvault.projectservice.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/internal/v1/projects")
@RequiredArgsConstructor
public class InternalProjectController {

    private final GetEnvironmentUseCase getEnvironmentUseCase;

    @GetMapping("/{projectUuid}/environments/{environmentUuid}")
    public ApiResponse<ProjectEnvironmentValidationResponse> getProjectEnvironment(
            @PathVariable UUID projectUuid,
            @PathVariable UUID environmentUuid
    ) {
        var result = getEnvironmentUseCase.getProjectEnvironment(projectUuid, environmentUuid);
        return ApiResponse.success(
                "Project environment validated successfully",
                ProjectEnvironmentValidationResponse.from(result)
        );
    }
}
