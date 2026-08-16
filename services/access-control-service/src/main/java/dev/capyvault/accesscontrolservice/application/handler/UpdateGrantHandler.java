package dev.capyvault.accesscontrolservice.application.handler;

import dev.capyvault.accesscontrolservice.application.command.UpdateGrantCommand;
import dev.capyvault.accesscontrolservice.application.port.out.AccessGrantRepository;
import dev.capyvault.accesscontrolservice.domain.AccessGrant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateGrantHandler {

    private final AccessGrantRepository repository;

    public UpdateGrantHandler(AccessGrantRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public AccessGrant handle(UpdateGrantCommand command) {
        AccessGrant existing = repository.findByUuid(command.grantId())
                .orElseThrow(() -> new IllegalArgumentException("Access grant not found"));

        AccessGrant updated = existing.update(
                command.role(),
                command.status()
        );

        return repository.save(updated);
    }
}