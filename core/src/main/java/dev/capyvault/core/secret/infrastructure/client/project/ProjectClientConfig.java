package dev.capyvault.core.secret.infrastructure.client.project;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ProjectClientConfig {
    @Bean
    RestClient projectRestClient(RestClient.Builder builder, @Value("${services.project.url}") String projectServiceUrl) {
        return builder.baseUrl(projectServiceUrl).build();
    }
}
