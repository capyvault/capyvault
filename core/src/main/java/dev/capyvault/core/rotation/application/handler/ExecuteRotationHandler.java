package dev.capyvault.core.rotation.application.handler;

import dev.capyvault.core.rotation.application.command.ExecuteRotationCommand;
import dev.capyvault.core.rotation.application.port.in.ExecuteRotationUseCase;
import dev.capyvault.core.rotation.application.port.out.RotationPolicyPersistencePort;
import dev.capyvault.core.rotation.domain.RotationPolicy;
import dev.capyvault.core.rotation.domain.RotationStrategy;
import dev.capyvault.core.rotation.web.RotationExecutionResponse;
import dev.capyvault.core.secret.application.command.RotateSecretCommand;
import dev.capyvault.core.secret.application.port.in.RotateSecretRequest;
import dev.capyvault.core.secret.application.port.in.RotateSecretResult;
import dev.capyvault.core.secret.application.port.in.RotateSecretUseCase;
import dev.capyvault.core.secret.web.SecretResponse;
import dev.capyvault.core.shared.exception.NotFoundException;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.UUID;
@Service
public class ExecuteRotationHandler implements ExecuteRotationUseCase {

    private static final String PASSWORD_CHARS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*";

    private final RotationPolicyPersistencePort rotationPolicyPersistencePort;
    private final RotateSecretUseCase rotateSecretUseCase;
    private final SecureRandom secureRandom = new SecureRandom();

    public ExecuteRotationHandler(
            RotationPolicyPersistencePort rotationPolicyPersistencePort,
            RotateSecretUseCase rotateSecretUseCase
    ) {
        this.rotationPolicyPersistencePort = rotationPolicyPersistencePort;
        this.rotateSecretUseCase = rotateSecretUseCase;
    }

    @Override
    @Transactional
    public RotationExecutionResponse execute(ExecuteRotationCommand command) throws BadRequestException {
        RotationPolicy policy = rotationPolicyPersistencePort.findBySecretId(command.secretId())
                .orElseThrow(() -> new NotFoundException("Rotation policy not found"));

        String rotatedValue = resolveRotatedValue(
                policy.strategy(),
                command.manualValue()
        );

        RotateSecretResult result = rotateSecretUseCase.rotate(
                new RotateSecretRequest(
                        command.secretId(),
                        rotatedValue
                )
        );

        policy.markRotated();

        RotationPolicy savedPolicy = rotationPolicyPersistencePort.save(policy);

        return new RotationExecutionResponse(
                result.secretId(),
                result.currentVersion(),
                result.rotatedAt(),
                savedPolicy.nextRotationAt()
        );
    }

    private String resolveRotatedValue(
            RotationStrategy strategy,
            String manualValue
    ) throws BadRequestException {
        if (strategy == RotationStrategy.MANUAL_VALUE) {
            if (manualValue == null || manualValue.isBlank()) {
                throw new BadRequestException("Manual rotation value is required");
            }

            return manualValue;
        }

        if (strategy == RotationStrategy.GENERATED_PASSWORD) {
            return generatePassword(32);
        }

        throw new BadRequestException("Unsupported rotation strategy");
    }

    private String generatePassword(int length) {
        StringBuilder builder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(PASSWORD_CHARS.length());
            builder.append(PASSWORD_CHARS.charAt(index));
        }

        return builder.toString();
    }
}