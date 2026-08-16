package dev.capyvault.secretservice.infrastructure.persistence.entity;

import dev.capyvault.secretservice.domain.secret.SecretStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "secrets", uniqueConstraints = @UniqueConstraint(name = "uk_secret_key_per_env", columnNames = {"project_uuid", "environment_uuid", "secret_key"}))
@Getter @Setter
public class SecretJpaEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private UUID uuid;
    @Column(name = "project_uuid", nullable = false)
    private UUID projectUuid;
    @Column(name = "environment_uuid", nullable = false)
    private UUID environmentUuid;
    @Column(name = "secret_key", nullable = false)
    private String key;
    private String description;
    @Enumerated(EnumType.STRING) @Column(nullable = false)
    private SecretStatus status;
    @Column(name = "created_by", nullable = false)
    private UUID createdBy;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "secret", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("versionNumber DESC")
    private List<SecretVersionJpaEntity> versions = new ArrayList<>();

    public void addVersion(SecretVersionJpaEntity version) {
        versions.add(version);
        version.setSecret(this);
    }
}
