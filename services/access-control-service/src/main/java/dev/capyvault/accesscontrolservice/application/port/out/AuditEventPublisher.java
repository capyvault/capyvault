package dev.capyvault.accesscontrolservice.application.port.out;

import java.util.Map;
import java.util.UUID;

public interface AuditEventPublisher {

    void publish(
            String eventType,
            UUID actorId,
            UUID projectId,
            String environment,
            Map<String, Object> metadata
    );
}