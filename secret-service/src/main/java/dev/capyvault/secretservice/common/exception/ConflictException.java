package dev.capyvault.secretservice.common.exception;

public class ConflictException extends BusinessException {
    public ConflictException(String message) { super("CONFLICT", message); }
}
