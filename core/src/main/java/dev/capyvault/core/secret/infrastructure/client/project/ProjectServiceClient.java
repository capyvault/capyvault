package dev.capyvault.core.secret.infrastructure.client.project;

import dev.capyvault.core.shared.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectServiceClient {
    private final RestClient projectRestClient;

    public ProjectEnvironmentResponse getEnvironment(UUID projectUuid, UUID environmentUuid) {
        try {
            ProjectServiceApiResponse<ProjectEnvironmentResponse> response = projectRestClient.get()
                    .uri("/internal/v1/projects/{projectUuid}/environments/{environmentUuid}", projectUuid, environmentUuid)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
            if (response == null || !response.success() || response.data() == null) {
                throw new BusinessException("PROJECT_SERVICE_INVALID_RESPONSE", "Project service returned invalid response");
            }
            return response.data();
        } catch (RestClientException ex) {
            throw new BusinessException("PROJECT_SERVICE_UNAVAILABLE", "Cannot validate project environment: " + ex.getMessage());
        }
    }
}
