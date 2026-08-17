package dev.capyvault.accesscontrolservice.infrastructure.audit;

import dev.capyvault.accesscontrolservice.application.port.out.AuditEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class LoggingAuditEventPublisher implements AuditEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(LoggingAuditEventPublisher.class);

    @Override
    public void publish(
            String eventType,
            UUID actorId,
            UUID projectId,
            String environment,
            Map<String, Object> metadata
    ) {
        log.info(
                "AUDIT_EVENT type={} actorId={} projectId={} environment={} metadata={}",
                eventType,
                actorId,
                projectId,
                environment,
                metadata
        );
    }
}