package dev.capyvault.core.secret.application.handler;

import dev.capyvault.core.secret.application.port.in.DeleteSecretUseCase;
import dev.capyvault.core.secret.application.port.out.SecretPersistencePort;
import dev.capyvault.core.secret.domain.Secret;
import dev.capyvault.core.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeleteSecretHandler implements DeleteSecretUseCase {

    private final SecretPersistencePort secretPersistencePort;

    public DeleteSecretHandler(SecretPersistencePort secretPersistencePort) {
        this.secretPersistencePort = secretPersistencePort;
    }

    @Override
    @Transactional
    public void delete(UUID secretId) {
        Secret secret = secretPersistencePort.findById(secretId)
                .orElseThrow(() -> new NotFoundException("Secret not found"));

        secret.delete();

        secretPersistencePort.save(secret);
    }
}
