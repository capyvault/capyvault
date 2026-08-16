package dev.capyvault.secretservice.api.secret;

import dev.capyvault.secretservice.application.query.SecretValueResult;
import java.util.UUID;

public record SecretValueResponse(UUID uuid, String key, String value, int version) { public static SecretValueResponse from(SecretValueResult r) { return new SecretValueResponse(r.uuid(), r.key(), r.value(), r.version()); } }
