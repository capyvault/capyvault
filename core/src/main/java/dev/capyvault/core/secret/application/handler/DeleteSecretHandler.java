package dev.capyvault.core.secret.application.handler;

import dev.capyvault.core.secret.application.port.in.DeleteSecretUseCase;
import dev.capyvault.core.secret.application.port.out.SecretPersistencePort;
import dev.capyvault.core.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class DeleteSecretHandler implements DeleteSecretUseCase {
    private final SecretPersistencePort secretPersistencePort;
    @Override
    public void delete(UUID secretUuid) {
//        var secret = secretPersistencePort.findByUuid(secretUuid).orElseThrow(() -> new NotFoundException("Secret not found"));
//        secret.delete();
//        secretPersistencePort.save(secret);
    }
}
