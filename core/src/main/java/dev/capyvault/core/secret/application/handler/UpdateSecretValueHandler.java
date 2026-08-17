package dev.capyvault.core.secret.application.handler;

import dev.capyvault.core.secret.application.command.UpdateSecretValueCommand;
import dev.capyvault.core.secret.application.port.in.UpdateSecretValueUseCase;
import dev.capyvault.core.secret.application.port.out.EncryptedSecretPayload;
import dev.capyvault.core.secret.application.port.out.SecretEncryptionPort;
import dev.capyvault.core.secret.application.port.out.SecretPersistencePort;
import dev.capyvault.core.secret.domain.EncryptedSecretValue;
import dev.capyvault.core.secret.domain.Secret;
import dev.capyvault.core.secret.web.SecretResponse;
import dev.capyvault.core.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateSecretValueHandler implements UpdateSecretValueUseCase {

    private final SecretPersistencePort secretPersistencePort;
    private final SecretEncryptionPort secretEncryptionPort;

    public UpdateSecretValueHandler(
            SecretPersistencePort secretPersistencePort,
            SecretEncryptionPort secretEncryptionPort
    ) {
        this.secretPersistencePort = secretPersistencePort;
        this.secretEncryptionPort = secretEncryptionPort;
    }

    @Override
    @Transactional
    public SecretResponse updateValue(UpdateSecretValueCommand command) {
        Secret secret = secretPersistencePort.findById(command.secretId())
                .orElseThrow(() -> new NotFoundException("Secret not found"));

        EncryptedSecretPayload payload = secretEncryptionPort.encrypt(command.newValue());

        EncryptedSecretValue encryptedValue = EncryptedSecretValue.from(payload);

        secret.addNewVersion(encryptedValue);

        Secret savedSecret = secretPersistencePort.save(secret);

        return SecretResponse.from(savedSecret);
    }
}