package dev.capyvault.core.rotation.application.port.in;

import dev.capyvault.core.rotation.application.command.CreateRotationPolicyCommand;
import dev.capyvault.core.rotation.web.RotationPolicyResponse;

public interface CreateRotationPolicyUseCase {

    RotationPolicyResponse create(CreateRotationPolicyCommand command);
}