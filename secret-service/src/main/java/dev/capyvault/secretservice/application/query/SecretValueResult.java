package dev.capyvault.secretservice.application.query;

import java.util.UUID;

public record SecretValueResult(UUID uuid, String key, String value, int version) {}
