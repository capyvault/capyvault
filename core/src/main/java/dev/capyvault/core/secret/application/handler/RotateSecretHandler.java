package dev.capyvault.core.secret.application.handler;

import dev.capyvault.core.secret.application.command.RotateSecretCommand;
import dev.capyvault.core.secret.application.port.in.RotateSecretRequest;
import dev.capyvault.core.secret.application.port.in.RotateSecretResult;
import dev.capyvault.core.secret.application.port.in.RotateSecretUseCase;
import dev.capyvault.core.secret.application.port.out.EncryptedSecretPayload;
import dev.capyvault.core.secret.application.port.out.SecretEncryptionPort;
import dev.capyvault.core.secret.application.port.out.SecretPersistencePort;
import dev.capyvault.core.secret.domain.EncryptedSecretValue;
import dev.capyvault.core.secret.domain.Secret;
import dev.capyvault.core.secret.web.SecretResponse;
import dev.capyvault.core.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class RotateSecretHandler implements RotateSecretUseCase {

    private final SecretPersistencePort secretPersistencePort;
    private final SecretEncryptionPort secretEncryptionPort;

    public RotateSecretHandler(
            SecretPersistencePort secretPersistencePort,
            SecretEncryptionPort secretEncryptionPort
    ) {
        this.secretPersistencePort = secretPersistencePort;
        this.secretEncryptionPort = secretEncryptionPort;
    }

    @Override
    @Transactional
    public RotateSecretResult rotate(RotateSecretRequest request) {
        Secret secret = secretPersistencePort.findById(request.secretId())
                .orElseThrow(() -> new NotFoundException("Secret not found"));

        EncryptedSecretPayload payload = secretEncryptionPort.encrypt(
                request.rotatedValue()
        );

        EncryptedSecretValue encryptedValue = EncryptedSecretValue.from(payload);

        secret.addNewVersion(encryptedValue);

        Secret savedSecret = secretPersistencePort.save(secret);

        return new RotateSecretResult(
                savedSecret.id(),
                savedSecret.currentVersion().versionNumber(),
                Instant.now()
        );
    }
}