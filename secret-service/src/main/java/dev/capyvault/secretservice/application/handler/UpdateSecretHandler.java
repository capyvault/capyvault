package dev.capyvault.secretservice.application.handler;

import dev.capyvault.secretservice.application.command.UpdateSecretCommand;
import dev.capyvault.secretservice.application.port.in.UpdateSecretUseCase;
import dev.capyvault.secretservice.application.port.out.SecretPersistencePort;
import dev.capyvault.secretservice.application.query.SecretResult;
import dev.capyvault.secretservice.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateSecretHandler implements UpdateSecretUseCase {
    private final SecretPersistencePort secretPersistencePort;
    @Override
    public SecretResult update(UpdateSecretCommand command) {
        var secret = secretPersistencePort.findByUuid(command.secretUuid())
                .orElseThrow(() -> new NotFoundException("Secret not found"));
        secret.updateMetadata(command.description());
        return SecretMapper.toResult(secretPersistencePort.save(secret));
    }
}
