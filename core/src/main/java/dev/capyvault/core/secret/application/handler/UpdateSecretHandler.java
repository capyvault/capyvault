package dev.capyvault.core.secret.application.handler;

import dev.capyvault.core.secret.application.command.UpdateSecretCommand;
import dev.capyvault.core.secret.application.port.in.UpdateSecretUseCase;
import dev.capyvault.core.secret.application.query.SecretResult;
import dev.capyvault.core.shared.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateSecretHandler implements UpdateSecretUseCase {
    //private final SecretPersistencePort secretPersistencePort;
    @Override
    public SecretResult update(UpdateSecretCommand command) {
//        var secret = secretPersistencePort.findByUuid(command.secretUuid())
//                .orElseThrow(() -> new NotFoundException("Secret not found"));
//        secret.updateMetadata(command.description());
//        return SecretMapper.toResult(secretPersistencePort.save(secret));
        return null;
    }
}
