package dev.capyvault.core.secret.application.handler;

import dev.capyvault.core.secret.application.port.in.ReadSecretValueUseCase;
import dev.capyvault.core.secret.application.query.SecretValueResult;
import dev.capyvault.core.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadSecretValueHandler implements ReadSecretValueUseCase {
//    private final SecretPersistencePort secretPersistencePort;
//    private final EncryptionPort encryptionPort;
    @Override
    public SecretValueResult read(UUID secretUuid) {
//        var secret = secretPersistencePort.findByUuid(secretUuid).orElseThrow(() -> new NotFoundException("Secret not found"));
//        var latest = secret.latestVersion();
//        String value = encryptionPort.decrypt(latest.getEncryptedValue());
//        return new SecretValueResult(secret.getUuid(), secret.getKey(), value, latest.getVersionNumber());
        return null;
    }
}
