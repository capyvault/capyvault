package dev.capyvault.core.secret.web;

import dev.capyvault.core.secret.application.command.CreateSecretCommand;
import dev.capyvault.core.secret.application.port.in.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/secrets")
@RequiredArgsConstructor
public class SecretController {

    private final CreateSecretUseCase createSecretUseCase;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SecretResponse create(@Valid @RequestBody CreateSecretRequest request) {
        return createSecretUseCase.create(
                new CreateSecretCommand(
                        request.projectId(),
                        request.environmentId(),
                        request.name(),
                        request.type(),
                        request.value()
                )
        );
    }

//    @GetMapping
//    public ApiResponse<List<SecretResponse>> list(@RequestParam UUID projectUuid, @RequestParam UUID environmentUuid) {
//        var data = getSecretUseCase.list(projectUuid, environmentUuid).stream().map(SecretResponse::from).toList();
//        return ApiResponse.success("Secrets retrieved successfully", data);
//    }
//
//    @GetMapping("/{secretUuid}")
//    public ApiResponse<SecretResponse> get(@PathVariable UUID secretUuid) {
//        return ApiResponse.success("Secret retrieved successfully", SecretResponse.from(getSecretUseCase.get(secretUuid)));
//    }
//
//    @PutMapping("/{secretUuid}")
//    public ApiResponse<SecretResponse> update(@PathVariable UUID secretUuid, @Valid @RequestBody UpdateSecretRequest request) {
//        return ApiResponse.success("Secret updated successfully", SecretResponse.from(updateSecretUseCase.update(new UpdateSecretCommand(secretUuid, request.description()))));
//    }
//
//    @DeleteMapping("/{secretUuid}")
//    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID secretUuid) {
//        deleteSecretUseCase.delete(secretUuid);
//        return ResponseEntity.ok(ApiResponse.success("Secret deleted successfully", null));
//    }
//
//    @GetMapping("/{secretUuid}/value")
//    public ApiResponse<SecretValueResponse> readValue(@PathVariable UUID secretUuid) {
//        return ApiResponse.success("Secret value retrieved successfully", SecretValueResponse.from(readSecretValueUseCase.read(secretUuid)));
//    }
//
//    @PostMapping("/{secretUuid}/versions")
//    public ApiResponse<SecretResponse> rotate(@PathVariable UUID secretUuid, @Valid @RequestBody RotateSecretRequest request) {
//        return ApiResponse.success("Secret rotated successfully", SecretResponse.from(rotateSecretUseCase.rotate(new RotateSecretCommand(secretUuid, request.value(), request.actorUuid()))));
//    }
//
//    @GetMapping("/{secretUuid}/versions")
//    public ApiResponse<List<SecretVersionResponse>> versions(@PathVariable UUID secretUuid) {
//        var data = getSecretVersionsUseCase.list(secretUuid).stream().map(SecretVersionResponse::from).toList();
//        return ApiResponse.success("Secret versions retrieved successfully", data);
//    }
}
