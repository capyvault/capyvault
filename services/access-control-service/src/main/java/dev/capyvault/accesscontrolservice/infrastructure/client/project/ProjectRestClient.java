package dev.capyvault.accesscontrolservice.infrastructure.client.project;

import dev.capyvault.accesscontrolservice.application.port.out.ProjectClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class ProjectRestClient implements ProjectClient {

    private final RestTemplate restTemplate;

    public ProjectRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean projectExists(UUID projectId) {
        try {
            ProjectExistsResponse response = restTemplate.getForObject(
                    "http://localhost:8082/internal/projects/" + projectId + "/exists",
                    ProjectExistsResponse.class
            );

            return response != null && response.exists();
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public boolean environmentExists(UUID projectId, String environment) {
        try {
            EnvironmentExistsResponse response = restTemplate.getForObject(
                    "http://localhost:8082/internal/projects/" + projectId + "/environments/" + environment + "/exists",
                    EnvironmentExistsResponse.class
            );

            return response != null && response.exists();
        } catch (Exception exception) {
            return false;
        }
    }
}