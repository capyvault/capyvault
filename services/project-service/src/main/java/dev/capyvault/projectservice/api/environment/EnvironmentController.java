package dev.capyvault.projectservice.api.environment;

import dev.capyvault.projectservice.application.command.CreateEnvironmentCommand;
import dev.capyvault.projectservice.application.command.UpdateEnvironmentCommand;
import dev.capyvault.projectservice.application.port.in.CreateEnvironmentUseCase;
import dev.capyvault.projectservice.application.port.in.DeleteEnvironmentUseCase;
import dev.capyvault.projectservice.application.port.in.GetEnvironmentUseCase;
import dev.capyvault.projectservice.application.port.in.UpdateEnvironmentUseCase;
import dev.capyvault.projectservice.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/projects/{projectUuid}/environments")
@RequiredArgsConstructor
public class EnvironmentController {

    private final CreateEnvironmentUseCase createEnvironmentUseCase;
    private final UpdateEnvironmentUseCase updateEnvironmentUseCase;
    private final GetEnvironmentUseCase getEnvironmentUseCase;
    private final DeleteEnvironmentUseCase deleteEnvironmentUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<EnvironmentResponse>> create(
            @PathVariable UUID projectUuid,
            @Valid @RequestBody CreateEnvironmentRequest request
    ) {
        var result = createEnvironmentUseCase.create(
                new CreateEnvironmentCommand(
                        projectUuid,
                        request.name(),
                        request.slug()
                )
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success("Environment created successfully", EnvironmentResponse.from(result)));
    }

    @GetMapping
    public ApiResponse<List<EnvironmentResponse>> list(@PathVariable UUID projectUuid) {
        var response = getEnvironmentUseCase.listByProject(projectUuid)
                .stream()
                .map(EnvironmentResponse::from)
                .toList();

        return ApiResponse.success("Environments retrieved successfully", response);
    }

    @GetMapping("/{environmentUuid}")
    public ApiResponse<EnvironmentResponse> get(
            @PathVariable UUID projectUuid,
            @PathVariable UUID environmentUuid
    ) {
        var result = getEnvironmentUseCase.get(projectUuid, environmentUuid);
        return ApiResponse.success("Environment retrieved successfully", EnvironmentResponse.from(result));
    }

    @PutMapping("/{environmentUuid}")
    public ApiResponse<EnvironmentResponse> update(
            @PathVariable UUID projectUuid,
            @PathVariable UUID environmentUuid,
            @Valid @RequestBody UpdateEnvironmentRequest request
    ) {
        var result = updateEnvironmentUseCase.update(
                new UpdateEnvironmentCommand(
                        projectUuid,
                        environmentUuid,
                        request.name(),
                        request.slug()
                )
        );

        return ApiResponse.success("Environment updated successfully", EnvironmentResponse.from(result));
    }

    @DeleteMapping("/{environmentUuid}")
    public ApiResponse<Void> delete(
            @PathVariable UUID projectUuid,
            @PathVariable UUID environmentUuid
    ) {
        deleteEnvironmentUseCase.delete(projectUuid, environmentUuid);
        return ApiResponse.success("Environment deleted successfully", null);
    }
}
