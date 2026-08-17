package dev.capyvault.core.secret.web;

import dev.capyvault.core.secret.application.command.CreateSecretCommand;
import dev.capyvault.core.secret.application.command.UpdateSecretValueCommand;
import dev.capyvault.core.secret.application.port.in.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/secrets")
public class SecretController {

    private final CreateSecretUseCase createSecretUseCase;
    private final GetSecretUseCase getSecretUseCase;
    private final GetSecretValueUseCase getSecretValueUseCase;
    private final UpdateSecretValueUseCase updateSecretValueUseCase;
    private final DeleteSecretUseCase deleteSecretUseCase;

    public SecretController(
            CreateSecretUseCase createSecretUseCase,
            GetSecretUseCase getSecretUseCase,
            GetSecretValueUseCase getSecretValueUseCase,
            UpdateSecretValueUseCase updateSecretValueUseCase,
            DeleteSecretUseCase deleteSecretUseCase
    ) {
        this.createSecretUseCase = createSecretUseCase;
        this.getSecretUseCase = getSecretUseCase;
        this.getSecretValueUseCase = getSecretValueUseCase;
        this.updateSecretValueUseCase = updateSecretValueUseCase;
        this.deleteSecretUseCase = deleteSecretUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SecretResponse create(@Valid @RequestBody CreateSecretRequest request) throws BadRequestException {
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

    @GetMapping("/{secretId}")
    public SecretResponse get(@PathVariable UUID secretId) {
        return getSecretUseCase.get(secretId);
    }

    @GetMapping("/{secretId}/value")
    public SecretValueResponse getValue(@PathVariable UUID secretId) {
        return getSecretValueUseCase.getValue(secretId);
    }

    @PutMapping("/{secretId}/value")
    public SecretResponse updateValue(
            @PathVariable UUID secretId,
            @Valid @RequestBody UpdateSecretValueRequest request
    ) {
        return updateSecretValueUseCase.updateValue(
                new UpdateSecretValueCommand(
                        secretId,
                        request.value()
                )
        );
    }

    @DeleteMapping("/{secretId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID secretId) {
        deleteSecretUseCase.delete(secretId);
    }
}