package dev.capyvault.core.secret.application.handler;

import dev.capyvault.core.secret.application.command.RotateSecretCommand;
import dev.capyvault.core.secret.application.port.in.RotateSecretUseCase;
import dev.capyvault.core.secret.application.query.SecretResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RotateSecretHandler implements RotateSecretUseCase {
//    private final SecretPersistencePort secretPersistencePort;
//    private final EncryptionPort encryptionPort;
    @Override
    public SecretResult rotate(RotateSecretCommand command) {
//        var secret = secretPersistencePort.findByUuid(command.secretUuid())
//                .orElseThrow(() -> new NotFoundException("Secret not found"));
//        var encrypted = encryptionPort.encrypt(command.value());
//        secret.addVersion(encrypted.ciphertext(), encrypted.keyId(), command.actorUuid());
//        return SecretMapper.toResult(secretPersistencePort.save(secret));
        return null;
    }
}
