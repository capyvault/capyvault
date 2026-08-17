package dev.capyvault.core.secret.infrastructure.persistence.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "secret_versions")
@Getter
@Setter
public class SecretVersionJpaEntity {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "secret_id", nullable = false)
    private SecretJpaEntity secret;

    @Column(name = "version_number", nullable = false)
    private int versionNumber;

    @Column(name = "ciphertext", nullable = false, columnDefinition = "TEXT")
    private String ciphertext;

    @Column(name = "key_id", nullable = false, length = 100)
    private String keyId;

    @Column(nullable = false, length = 100)
    private String algorithm;

    @Column(nullable = false, length = 255)
    private String nonce;

    @Column(name = "is_current", nullable = false)
    private boolean current;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected SecretVersionJpaEntity() {
    }

    public SecretVersionJpaEntity(
            UUID id,
            int versionNumber,
            String ciphertext,
            String keyId,
            String algorithm,
            String nonce,
            boolean current,
            Instant createdAt
    ) {
        this.id = id;
        this.versionNumber = versionNumber;
        this.ciphertext = ciphertext;
        this.keyId = keyId;
        this.algorithm = algorithm;
        this.nonce = nonce;
        this.current = current;
        this.createdAt = createdAt;
    }
}