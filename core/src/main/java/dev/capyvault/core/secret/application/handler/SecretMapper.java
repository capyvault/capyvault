package dev.capyvault.core.secret.application.handler;


import dev.capyvault.core.secret.application.query.SecretResult;
import dev.capyvault.core.secret.application.query.SecretVersionResult;
import dev.capyvault.core.secret.domain.Secret;
import dev.capyvault.core.secret.domain.SecretVersion;

public class SecretMapper {
//    public static SecretResult toResult(Secret secret) {
//        int latest = secret.versions().stream().map(SecretVersion::versionNumber).max(Integer::compareTo).orElse(0);
//        return new SecretResult(secret.id(), secret.projectId(), secret.environmentId());
//    }
//    public static SecretVersionResult toVersionResult(SecretVersion version) {
//        return new SecretVersionResult(version.getUuid(), version.getVersionNumber(), version.getEncryptionKeyId(),
//                version.getCreatedBy(), version.getCreatedAt());
//    }
}
