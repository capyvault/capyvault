package dev.capyvault.accesscontrolservice.application.port.out;


import dev.capyvault.accesscontrolservice.domain.AccessGrant;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccessGrantRepository {

    AccessGrant save(AccessGrant grant);

    Optional<AccessGrant> findByUuid(UUID uuid);

    Optional<AccessGrant> findByProjectIdAndUserIdAndEnvironment(
            UUID projectId,
            UUID userId,
            String environment
    );

    List<AccessGrant> findByProjectId(UUID projectId);

    void deleteByUuid(UUID uuid);

    boolean existsByProjectIdAndUserIdAndEnvironment(
            UUID projectId,
            UUID userId,
            String environment
    );
}