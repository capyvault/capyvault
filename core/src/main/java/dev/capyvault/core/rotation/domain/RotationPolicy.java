package dev.capyvault.core.rotation.domain;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public final class RotationPolicy {

    private final UUID id;
    private final UUID secretId;
    private final int intervalDays;
    private final RotationStrategy strategy;
    private RotationStatus status;
    private Instant lastRotatedAt;
    private Instant nextRotationAt;
    private final Instant createdAt;
    private Instant updatedAt;

    private RotationPolicy(
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
        if (intervalDays < 1) {
            throw new IllegalArgumentException("Rotation interval days must be greater than zero");
        }

        this.id = Objects.requireNonNull(id);
        this.secretId = Objects.requireNonNull(secretId);
        this.intervalDays = intervalDays;
        this.strategy = Objects.requireNonNull(strategy);
        this.status = Objects.requireNonNull(status);
        this.lastRotatedAt = lastRotatedAt;
        this.nextRotationAt = Objects.requireNonNull(nextRotationAt);
        this.createdAt = Objects.requireNonNull(createdAt);
        this.updatedAt = Objects.requireNonNull(updatedAt);
    }

    public static RotationPolicy create(
            UUID secretId,
            int intervalDays,
            RotationStrategy strategy
    ) {
        Instant now = Instant.now();

        return new RotationPolicy(
                UUID.randomUUID(),
                secretId,
                intervalDays,
                strategy,
                RotationStatus.ENABLED,
                null,
                now.plusSeconds(intervalDays * 24L * 60L * 60L),
                now,
                now
        );
    }

    public static RotationPolicy restore(
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
        return new RotationPolicy(
                id,
                secretId,
                intervalDays,
                strategy,
                status,
                lastRotatedAt,
                nextRotationAt,
                createdAt,
                updatedAt
        );
    }

    public void markRotated() {
        Instant now = Instant.now();

        this.lastRotatedAt = now;
        this.nextRotationAt = now.plusSeconds(intervalDays * 24L * 60L * 60L);
        this.updatedAt = now;
    }

    public boolean isDue() {
        return status == RotationStatus.ENABLED
                && !nextRotationAt.isAfter(Instant.now());
    }

    public UUID id() {
        return id;
    }

    public UUID secretId() {
        return secretId;
    }

    public int intervalDays() {
        return intervalDays;
    }

    public RotationStrategy strategy() {
        return strategy;
    }

    public RotationStatus status() {
        return status;
    }

    public Instant lastRotatedAt() {
        return lastRotatedAt;
    }

    public Instant nextRotationAt() {
        return nextRotationAt;
    }

    public Instant createdAt() {
        return createdAt;
    }

    public Instant updatedAt() {
        return updatedAt;
    }
}
