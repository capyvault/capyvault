package dev.capyvault.core.secret.application.handler;

import dev.capyvault.core.secret.application.command.CreateSecretCommand;
import dev.capyvault.core.secret.application.port.in.CreateSecretUseCase;
import dev.capyvault.core.secret.application.port.out.SecretEncryptionPort;
import dev.capyvault.core.secret.application.port.out.SecretPersistencePort;
import dev.capyvault.core.secret.domain.EncryptedSecretValue;
import dev.capyvault.core.secret.domain.Secret;
import dev.capyvault.core.secret.web.SecretResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateSecretHandler implements CreateSecretUseCase {

    private final SecretEncryptionPort secretEncryptionPort;
    private final SecretPersistencePort secretPersistencePort;

    public CreateSecretHandler(
            SecretEncryptionPort secretEncryptionPort,
            SecretPersistencePort secretPersistencePort
    ) {
        this.secretEncryptionPort = secretEncryptionPort;
        this.secretPersistencePort = secretPersistencePort;
    }

    @Override
    @Transactional
    public SecretResponse create(CreateSecretCommand command) {
        EncryptedSecretValue encryptedValue = secretEncryptionPort.encrypt(command.value());

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