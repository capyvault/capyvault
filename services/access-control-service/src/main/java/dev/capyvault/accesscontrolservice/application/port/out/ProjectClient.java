package dev.capyvault.accesscontrolservice.application.port.out;

import java.util.UUID;

public interface ProjectClient {

    boolean projectExists(UUID projectId);

    boolean environmentExists(UUID projectId, String environment);
}