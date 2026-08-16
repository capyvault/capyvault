package dev.capyvault.secretservice.infrastructure.client.project;

public record ProjectServiceApiResponse<T>(boolean success, String code, String message, T data, String timestamp) {}
