package dev.capyvault.core.secret.application.handler;

import dev.capyvault.core.secret.application.port.in.GetSecretUseCase;
import dev.capyvault.core.secret.application.port.out.SecretPersistencePort;
import dev.capyvault.core.secret.domain.Secret;
import dev.capyvault.core.secret.web.SecretResponse;
import dev.capyvault.core.shared.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class GetSecretHandler implements GetSecretUseCase {

    private final SecretPersistencePort secretPersistencePort;

    public GetSecretHandler(SecretPersistencePort secretPersistencePort) {
        this.secretPersistencePort = secretPersistencePort;
    }

    @Override
    @Transactional(readOnly = true)
    public SecretResponse get(UUID secretId) {
        Secret secret = secretPersistencePort.findById(secretId)
                .orElseThrow(() -> new NotFoundException("Secret not found"));

        return SecretResponse.from(secret);
    }
}
