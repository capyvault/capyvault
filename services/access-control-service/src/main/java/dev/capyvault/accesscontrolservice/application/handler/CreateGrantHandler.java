package dev.capyvault.accesscontrolservice.application.handler;

import dev.capyvault.accesscontrolservice.application.command.CreateGrantCommand;
import dev.capyvault.accesscontrolservice.application.port.out.AccessGrantRepository;
import dev.capyvault.accesscontrolservice.application.port.out.ProjectClient;
import dev.capyvault.accesscontrolservice.domain.AccessGrant;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateGrantHandler {

    private final AccessGrantRepository repository;
    private final ProjectClient projectClient;


    public CreateGrantHandler(
            AccessGrantRepository repository,

            @Qualifier("fakeProjectRestClient") ProjectClient projectClient) {
        this.repository = repository;
        this.projectClient = projectClient;
    }

    @Transactional
    public AccessGrant handle(CreateGrantCommand command) {
        boolean projectExists = projectClient.projectExists(command.projectId());

        if (!projectExists) {
            throw new IllegalArgumentException("Project does not exist");
        }

        boolean environmentExists = projectClient.environmentExists(
                command.projectId(),
                command.environment()
        );

        if (!environmentExists) {
            throw new IllegalArgumentException("Environment does not exist in this project");
        }

        boolean exists = repository.existsByProjectIdAndUserIdAndEnvironment(
                command.projectId(),
                command.userId(),
                command.environment()
        );

        if (exists) {
            throw new IllegalStateException("Access grant already exists for this user, project, and environment");
        }

        AccessGrant grant = AccessGrant.create(
                command.projectId(),
                command.userId(),
                command.environment(),
                command.role()
        );

        return repository.save(grant);
    }
}