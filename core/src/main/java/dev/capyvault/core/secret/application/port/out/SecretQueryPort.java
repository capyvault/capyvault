package dev.capyvault.core.secret.application.port.out;


import dev.capyvault.core.secret.web.SecretResponse;

import java.util.List;
import java.util.UUID;

public interface SecretQueryPort {

    List<SecretResponse> search(UUID projectId, UUID environmentId);
}