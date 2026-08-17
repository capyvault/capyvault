package dev.capyvault.core.rotation.application.port.in;

import dev.capyvault.core.rotation.application.command.ExecuteRotationCommand;
import dev.capyvault.core.rotation.web.RotationExecutionResponse;
import dev.capyvault.core.secret.web.SecretResponse;
import org.apache.coyote.BadRequestException;

public interface ExecuteRotationUseCase {

    RotationExecutionResponse execute(ExecuteRotationCommand command) throws BadRequestException;
}