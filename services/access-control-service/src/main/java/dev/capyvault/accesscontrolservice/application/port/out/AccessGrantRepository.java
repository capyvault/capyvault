package dev.capyvault.accesscontrolservice.application.port.out;

import dev.capyvault.accesscontrolservice.domain.AccessGrant;
import dev.capyvault.accesscontrolservice.domain.PrincipalType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccessGrantRepository {

    AccessGrant save(AccessGrant grant);

    Optional<AccessGrant> findByUuid(UUID uuid);

    List<AccessGrant> findByProjectId(UUID projectId);

    List<AccessGrant> findByPrincipal(
            UUID principalId,
            PrincipalType principalType
    );

    List<AccessGrant> findCandidateGrants(
            UUID principalId,
            PrincipalType principalType,
            UUID projectId
    );

    boolean existsDuplicate(
            UUID principalId,
            PrincipalType principalType,
            UUID projectId,
            String environment
    );

    void deleteByUuid(UUID uuid);
}