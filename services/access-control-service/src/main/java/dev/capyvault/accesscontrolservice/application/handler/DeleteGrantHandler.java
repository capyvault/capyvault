package dev.capyvault.accesscontrolservice.application.handler;

import dev.capyvault.accesscontrolservice.application.port.out.AccessGrantRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeleteGrantHandler {

    private final AccessGrantRepository repository;

    public DeleteGrantHandler(AccessGrantRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void handle(UUID grantId) {
        repository.deleteByUuid(grantId);
    }
}