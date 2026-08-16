package dev.capyvault.core.secret.infrastructure.client.project;

import dev.capyvault.core.secret.application.port.out.ProjectPort;
import dev.capyvault.core.secret.application.query.ProjectEnvironmentInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProjectClientAdapter implements ProjectPort {
    private final ProjectServiceClient client;
    @Override
    public ProjectEnvironmentInfo getEnvironment(UUID projectUuid, UUID environmentUuid) {
        var r = client.getEnvironment(projectUuid, environmentUuid);
        return new ProjectEnvironmentInfo(r.projectUuid(), r.projectStatus(), r.environmentUuid(), r.environmentName(),
                r.environmentSlug(), r.environmentStatus(), r.available());
    }
}
