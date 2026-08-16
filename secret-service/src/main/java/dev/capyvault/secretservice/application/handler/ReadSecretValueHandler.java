package dev.capyvault.secretservice.application.handler;

import dev.capyvault.secretservice.application.port.in.ReadSecretValueUseCase;
import dev.capyvault.secretservice.application.port.out.EncryptionPort;
import dev.capyvault.secretservice.application.port.out.SecretPersistencePort;
import dev.capyvault.secretservice.application.query.SecretValueResult;
import dev.capyvault.secretservice.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReadSecretValueHandler implements ReadSecretValueUseCase {
    private final SecretPersistencePort secretPersistencePort;
    private final EncryptionPort encryptionPort;
    @Override
    public SecretValueResult read(UUID secretUuid) {
        var secret = secretPersistencePort.findByUuid(secretUuid).orElseThrow(() -> new NotFoundException("Secret not found"));
        var latest = secret.latestVersion();
        String value = encryptionPort.decrypt(latest.getEncryptedValue());
        return new SecretValueResult(secret.getUuid(), secret.getKey(), value, latest.getVersionNumber());
    }
}
