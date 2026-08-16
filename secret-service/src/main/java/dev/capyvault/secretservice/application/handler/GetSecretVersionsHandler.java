package dev.capyvault.secretservice.application.handler;

import dev.capyvault.secretservice.application.port.in.GetSecretVersionsUseCase;
import dev.capyvault.secretservice.application.port.out.SecretPersistencePort;
import dev.capyvault.secretservice.application.query.SecretVersionResult;
import dev.capyvault.secretservice.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetSecretVersionsHandler implements GetSecretVersionsUseCase {
    private final SecretPersistencePort secretPersistencePort;
    @Override
    public List<SecretVersionResult> list(UUID secretUuid) {
        var secret = secretPersistencePort.findByUuid(secretUuid).orElseThrow(() -> new NotFoundException("Secret not found"));
        return secret.getVersions().stream()
                .sorted(Comparator.comparingInt(v -> -v.getVersionNumber()))
                .map(SecretMapper::toVersionResult).toList();
    }
}
