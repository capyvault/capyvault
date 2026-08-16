package dev.capyvault.secretservice.api.secret;

import dev.capyvault.secretservice.application.command.CreateSecretCommand;
import dev.capyvault.secretservice.application.command.RotateSecretCommand;
import dev.capyvault.secretservice.application.command.UpdateSecretCommand;
import dev.capyvault.secretservice.application.port.in.*;
import dev.capyvault.secretservice.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/secrets")
@RequiredArgsConstructor
public class SecretController {
    private final CreateSecretUseCase createSecretUseCase;
    private final UpdateSecretUseCase updateSecretUseCase;
    private final GetSecretUseCase getSecretUseCase;
    private final ReadSecretValueUseCase readSecretValueUseCase;
    private final RotateSecretUseCase rotateSecretUseCase;
    private final DeleteSecretUseCase deleteSecretUseCase;
    private final GetSecretVersionsUseCase getSecretVersionsUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<SecretResponse>> create(@Valid @RequestBody CreateSecretRequest request) {
        var result = createSecretUseCase.create(new CreateSecretCommand(request.projectUuid(), request.environmentUuid(),
                request.key(), request.value(), request.description(), request.actorUuid()));
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Secret created successfully", SecretResponse.from(result)));
    }

    @GetMapping
    public ApiResponse<List<SecretResponse>> list(@RequestParam UUID projectUuid, @RequestParam UUID environmentUuid) {
        var data = getSecretUseCase.list(projectUuid, environmentUuid).stream().map(SecretResponse::from).toList();
        return ApiResponse.success("Secrets retrieved successfully", data);
    }

    @GetMapping("/{secretUuid}")
    public ApiResponse<SecretResponse> get(@PathVariable UUID secretUuid) {
        return ApiResponse.success("Secret retrieved successfully", SecretResponse.from(getSecretUseCase.get(secretUuid)));
    }

    @PutMapping("/{secretUuid}")
    public ApiResponse<SecretResponse> update(@PathVariable UUID secretUuid, @Valid @RequestBody UpdateSecretRequest request) {
        return ApiResponse.success("Secret updated successfully", SecretResponse.from(updateSecretUseCase.update(new UpdateSecretCommand(secretUuid, request.description()))));
    }

    @DeleteMapping("/{secretUuid}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID secretUuid) {
        deleteSecretUseCase.delete(secretUuid);
        return ResponseEntity.ok(ApiResponse.success("Secret deleted successfully", null));
    }

    @GetMapping("/{secretUuid}/value")
    public ApiResponse<SecretValueResponse> readValue(@PathVariable UUID secretUuid) {
        return ApiResponse.success("Secret value retrieved successfully", SecretValueResponse.from(readSecretValueUseCase.read(secretUuid)));
    }

    @PostMapping("/{secretUuid}/versions")
    public ApiResponse<SecretResponse> rotate(@PathVariable UUID secretUuid, @Valid @RequestBody RotateSecretRequest request) {
        return ApiResponse.success("Secret rotated successfully", SecretResponse.from(rotateSecretUseCase.rotate(new RotateSecretCommand(secretUuid, request.value(), request.actorUuid()))));
    }

    @GetMapping("/{secretUuid}/versions")
    public ApiResponse<List<SecretVersionResponse>> versions(@PathVariable UUID secretUuid) {
        var data = getSecretVersionsUseCase.list(secretUuid).stream().map(SecretVersionResponse::from).toList();
        return ApiResponse.success("Secret versions retrieved successfully", data);
    }
}
