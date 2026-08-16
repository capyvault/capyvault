package dev.capyvault.accesscontrolservice.application.handler;

import dev.capyvault.accesscontrolservice.application.port.out.AccessGrantRepository;
import dev.capyvault.accesscontrolservice.domain.AccessGrant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class FindProjectGrantsHandler {

    private final AccessGrantRepository repository;

    public FindProjectGrantsHandler(AccessGrantRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<AccessGrant> handle(UUID projectId) {
        return repository.findByProjectId(projectId);
    }
}