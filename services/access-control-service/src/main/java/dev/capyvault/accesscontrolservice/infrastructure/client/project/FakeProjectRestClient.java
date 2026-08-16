package dev.capyvault.accesscontrolservice.infrastructure.client.project;

import dev.capyvault.accesscontrolservice.application.port.out.ProjectClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class FakeProjectRestClient implements ProjectClient {

    @Override
    public boolean projectExists(UUID projectId) {
       return true;
    }

    @Override
    public boolean environmentExists(UUID projectId, String environment) {
        return true;
    }
}