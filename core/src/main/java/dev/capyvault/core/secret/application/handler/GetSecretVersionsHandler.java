package dev.capyvault.core.secret.application.handler;

import dev.capyvault.core.secret.application.port.in.GetSecretVersionsUseCase;
import dev.capyvault.core.secret.application.query.SecretVersionResult;
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
    //private final SecretPersistencePort secretPersistencePort;
    @Override
    public List<SecretVersionResult> list(UUID secretUuid) {
//        var secret = secretPersistencePort.findByUuid(secretUuid).orElseThrow(() -> new NotFoundException("Secret not found"));
//        return secret.getVersions().stream()
//                .sorted(Comparator.comparingInt(v -> -v.getVersionNumber()))
//                .map(SecretMapper::toVersionResult).toList();
        return null;
    }
}
