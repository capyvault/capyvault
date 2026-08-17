package dev.capyvault.core.secret.application.handler;

import dev.capyvault.core.secret.application.command.CreateSecretCommand;
import dev.capyvault.core.secret.application.port.in.CreateSecretUseCase;
import dev.capyvault.core.secret.application.port.out.EncryptedSecretPayload;
import dev.capyvault.core.secret.application.port.out.SecretEncryptionPort;
import dev.capyvault.core.secret.application.port.out.SecretPersistencePort;
import dev.capyvault.core.secret.domain.EncryptedSecretValue;
import dev.capyvault.core.secret.domain.Secret;
import dev.capyvault.core.secret.web.SecretResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateSecretHandler implements CreateSecretUseCase {

    private final SecretPersistencePort secretPersistencePort;
    private final SecretEncryptionPort secretEncryptionPort;

    public CreateSecretHandler(
            SecretPersistencePort secretPersistencePort,
            SecretEncryptionPort secretEncryptionPort
    ) {
        this.secretPersistencePort = secretPersistencePort;
        this.secretEncryptionPort = secretEncryptionPort;
    }

    @Override
    @Transactional
    public SecretResponse create(CreateSecretCommand command) throws BadRequestException {
        boolean exists = secretPersistencePort.existsByProjectIdAndEnvironmentIdAndName(
                command.projectId(),
                command.environmentId(),
                command.name()
        );

        if (exists) {
            throw new BadRequestException("Secret name already exists in this environment");
        }

        EncryptedSecretPayload payload = secretEncryptionPort.encrypt(command.value());
        EncryptedSecretValue encryptedValue = EncryptedSecretValue.from(payload);

        Secret secret = Secret.create(
                command.projectId(),
                command.environmentId(),
                command.name(),
                command.type(),
                encryptedValue
        );

        Secret savedSecret = secretPersistencePort.save(secret);

        return SecretResponse.from(savedSecret);
    }
}