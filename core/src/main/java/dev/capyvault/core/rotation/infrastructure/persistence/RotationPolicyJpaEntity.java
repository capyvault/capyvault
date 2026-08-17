package dev.capyvault.core.rotation.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Entity
@Table(name = "rotation_policies")
public class RotationPolicyJpaEntity {

    @Id
    private UUID id;

    @Column(name = "secret_id", nullable = false, unique = true)
    private UUID secretId;

    @Column(name = "interval_days", nullable = false)
    private int intervalDays;

    @Column(nullable = false, length = 50)
    private String strategy;

    @Column(nullable = false, length = 50)
    private String status;

    @Column(name = "last_rotated_at")
    private Instant lastRotatedAt;

    @Column(name = "next_rotation_at", nullable = false)
    private Instant nextRotationAt;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected RotationPolicyJpaEntity() {
    }

    public RotationPolicyJpaEntity(
            UUID id,
            UUID secretId,
            int intervalDays,
            String strategy,
            String status,
            Instant lastRotatedAt,
            Instant nextRotationAt,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.secretId = secretId;
        this.intervalDays = intervalDays;
        this.strategy = strategy;
        this.status = status;
        this.lastRotatedAt = lastRotatedAt;
        this.nextRotationAt = nextRotationAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}