package dev.capyvault.identityservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI identityServiceOpenAPI() {

        return new OpenAPI()
                .info(
                        new Info()
                                .title("CapyVault Identity Service API")
                                .description(
                                        "Authentication and identity management API for CapyVault."
                                )
                                .version("v1")
                );
    }
}