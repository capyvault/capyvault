package dev.capyvault.core.rotation.application.handler;

import dev.capyvault.core.rotation.application.command.CreateRotationPolicyCommand;
import dev.capyvault.core.rotation.application.port.in.CreateRotationPolicyUseCase;
import dev.capyvault.core.rotation.application.port.out.RotationPolicyPersistencePort;
import dev.capyvault.core.rotation.domain.RotationPolicy;
import dev.capyvault.core.rotation.web.RotationPolicyResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateRotationPolicyHandler implements CreateRotationPolicyUseCase {

    private final RotationPolicyPersistencePort rotationPolicyPersistencePort;

    public CreateRotationPolicyHandler(
            RotationPolicyPersistencePort rotationPolicyPersistencePort
    ) {
        this.rotationPolicyPersistencePort = rotationPolicyPersistencePort;
    }

    @Override
    @Transactional
    public RotationPolicyResponse create(CreateRotationPolicyCommand command) {
        RotationPolicy policy = RotationPolicy.create(
                command.secretId(),
                command.intervalDays(),
                command.strategy()
        );

        RotationPolicy savedPolicy = rotationPolicyPersistencePort.save(policy);

        return RotationPolicyResponse.from(savedPolicy);
    }
}