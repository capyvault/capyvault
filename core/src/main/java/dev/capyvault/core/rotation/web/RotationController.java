package dev.capyvault.core.rotation.web;

import dev.capyvault.core.rotation.application.command.CreateRotationPolicyCommand;
import dev.capyvault.core.rotation.application.command.ExecuteRotationCommand;
import dev.capyvault.core.rotation.application.port.in.CreateRotationPolicyUseCase;
import dev.capyvault.core.rotation.application.port.in.ExecuteRotationUseCase;
import dev.capyvault.core.secret.web.SecretResponse;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/api/v1/rotation-policies")
public class RotationController {

    private final CreateRotationPolicyUseCase createRotationPolicyUseCase;
    private final ExecuteRotationUseCase executeRotationUseCase;

    public RotationController(
            CreateRotationPolicyUseCase createRotationPolicyUseCase,
            ExecuteRotationUseCase executeRotationUseCase
    ) {
        this.createRotationPolicyUseCase = createRotationPolicyUseCase;
        this.executeRotationUseCase = executeRotationUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RotationPolicyResponse create(
            @Valid @RequestBody CreateRotationPolicyRequest request
    ) {
        return createRotationPolicyUseCase.create(
                new CreateRotationPolicyCommand(
                        request.secretId(),
                        request.intervalDays(),
                        request.strategy()
                )
        );
    }

    @PostMapping("/secrets/{secretId}/execute")
    public RotationExecutionResponse execute(
            @PathVariable UUID secretId,
            @RequestParam(required = false) String value
    ) throws BadRequestException {
        return executeRotationUseCase.execute(
                new ExecuteRotationCommand(
                        secretId,
                        value
                )
        );
    }
}