package dev.capyvault.core.rotation.web;

import dev.capyvault.core.rotation.domain.RotationPolicy;
import dev.capyvault.core.rotation.domain.RotationStatus;
import dev.capyvault.core.rotation.domain.RotationStrategy;

import java.time.Instant;
import java.util.UUID;

public record RotationPolicyResponse(
        UUID id,
        UUID secretId,
        int intervalDays,
        RotationStrategy strategy,
        RotationStatus status,
        Instant lastRotatedAt,
        Instant nextRotationAt,
        Instant createdAt,
        Instant updatedAt
) {

    public static RotationPolicyResponse from(RotationPolicy policy) {
        return new RotationPolicyResponse(
                policy.id(),
                policy.secretId(),
                policy.intervalDays(),
                policy.strategy(),
                policy.status(),
                policy.lastRotatedAt(),
                policy.nextRotationAt(),
                policy.createdAt(),
                policy.updatedAt()
        );
    }
}