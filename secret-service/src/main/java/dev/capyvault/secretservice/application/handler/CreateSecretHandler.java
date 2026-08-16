package dev.capyvault.secretservice.application.handler;

import dev.capyvault.secretservice.application.command.CreateSecretCommand;
import dev.capyvault.secretservice.application.port.in.CreateSecretUseCase;
import dev.capyvault.secretservice.application.port.out.EncryptionPort;
import dev.capyvault.secretservice.application.port.out.ProjectPort;
import dev.capyvault.secretservice.application.port.out.SecretPersistencePort;
import dev.capyvault.secretservice.application.query.SecretResult;
import dev.capyvault.secretservice.common.exception.BusinessException;
import dev.capyvault.secretservice.common.exception.ConflictException;
import dev.capyvault.secretservice.domain.secret.Secret;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateSecretHandler implements CreateSecretUseCase {
    private final ProjectPort projectPort;
    private final SecretPersistencePort secretPersistencePort;
    private final EncryptionPort encryptionPort;

    @Override
    public SecretResult create(CreateSecretCommand command) {
        var environment = projectPort.getEnvironment(command.projectUuid(), command.environmentUuid());
        if (!environment.available()) {
            throw new BusinessException("PROJECT_ENVIRONMENT_UNAVAILABLE", "Project environment is not active or available");
        }
        String normalizedKey = command.key().trim().toUpperCase();
        if (secretPersistencePort.exists(command.projectUuid(), command.environmentUuid(), normalizedKey)) {
            throw new ConflictException("Secret key already exists in this project environment");
        }
        var encrypted = encryptionPort.encrypt(command.value());
        Secret secret = Secret.create(UUID.randomUUID(), command.projectUuid(), command.environmentUuid(), normalizedKey,
                command.description(), command.actorUuid());
        secret.addVersion(encrypted.ciphertext(), encrypted.keyId(), command.actorUuid());
        return SecretMapper.toResult(secretPersistencePort.save(secret));
    }
}
