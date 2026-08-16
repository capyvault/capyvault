package dev.capyvault.secretservice.application.handler;

import dev.capyvault.secretservice.application.command.RotateSecretCommand;
import dev.capyvault.secretservice.application.port.in.RotateSecretUseCase;
import dev.capyvault.secretservice.application.port.out.EncryptionPort;
import dev.capyvault.secretservice.application.port.out.SecretPersistencePort;
import dev.capyvault.secretservice.application.query.SecretResult;
import dev.capyvault.secretservice.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RotateSecretHandler implements RotateSecretUseCase {
    private final SecretPersistencePort secretPersistencePort;
    private final EncryptionPort encryptionPort;
    @Override
    public SecretResult rotate(RotateSecretCommand command) {
        var secret = secretPersistencePort.findByUuid(command.secretUuid())
                .orElseThrow(() -> new NotFoundException("Secret not found"));
        var encrypted = encryptionPort.encrypt(command.value());
        secret.addVersion(encrypted.ciphertext(), encrypted.keyId(), command.actorUuid());
        return SecretMapper.toResult(secretPersistencePort.save(secret));
    }
}
