package dev.capyvault.core.secret.application.handler;

import dev.capyvault.core.secret.application.port.in.GetSecretValueUseCase;
import dev.capyvault.core.secret.application.port.out.SecretEncryptionPort;
import dev.capyvault.core.secret.application.port.out.SecretPersistencePort;
import dev.capyvault.core.secret.domain.Secret;
import dev.capyvault.core.secret.web.SecretValueResponse;
import dev.capyvault.core.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GetSecretValueHandler implements GetSecretValueUseCase {

    private final SecretPersistencePort secretPersistencePort;
    private final SecretEncryptionPort secretEncryptionPort;

    public GetSecretValueHandler(
            SecretPersistencePort secretPersistencePort,
            SecretEncryptionPort secretEncryptionPort
    ) {
        this.secretPersistencePort = secretPersistencePort;
        this.secretEncryptionPort = secretEncryptionPort;
    }

    @Override
    @Transactional(readOnly = true)
    public SecretValueResponse getValue(UUID secretId) {
        Secret secret = secretPersistencePort.findById(secretId)
                .orElseThrow(() -> new NotFoundException("Secret not found"));

        secret.ensureReadable();

        String plaintext = secretEncryptionPort.decrypt(
        secret.currentVersion()
                .encryptedValue()
                .toPayload()
        );

        return new SecretValueResponse(
                secret.id(),
                secret.name(),
                secret.currentVersion().versionNumber(),
                plaintext
        );
    }
}
