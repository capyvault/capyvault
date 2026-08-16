package dev.capyvault.secretservice.application.handler;

import dev.capyvault.secretservice.application.port.in.GetSecretUseCase;
import dev.capyvault.secretservice.application.port.out.SecretPersistencePort;
import dev.capyvault.secretservice.application.query.SecretResult;
import dev.capyvault.secretservice.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetSecretHandler implements GetSecretUseCase {
    private final SecretPersistencePort secretPersistencePort;
    @Override
    public SecretResult get(UUID secretUuid) {
        return SecretMapper.toResult(secretPersistencePort.findByUuid(secretUuid)
                .orElseThrow(() -> new NotFoundException("Secret not found")));
    }
    @Override
    public List<SecretResult> list(UUID projectUuid, UUID environmentUuid) {
        return secretPersistencePort.findByProjectUuidAndEnvironmentUuid(projectUuid, environmentUuid)
                .stream().map(SecretMapper::toResult).toList();
    }
}
