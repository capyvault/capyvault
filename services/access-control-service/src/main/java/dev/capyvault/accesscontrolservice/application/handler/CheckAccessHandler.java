package dev.capyvault.accesscontrolservice.application.handler;

import dev.capyvault.accesscontrolservice.application.command.CheckAccessCommand;
import dev.capyvault.accesscontrolservice.application.port.out.AccessGrantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckAccessHandler {

    private final AccessGrantRepository repository;

    public CheckAccessHandler(AccessGrantRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public boolean handle(CheckAccessCommand command) {
        return repository
                .findByProjectIdAndUserIdAndEnvironment(
                        command.projectId(),
                        command.userId(),
                        command.environment()
                )
                .map(grant -> grant.allows(command.action()))
                .orElse(false);
    }
}