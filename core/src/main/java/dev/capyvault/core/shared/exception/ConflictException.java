package dev.capyvault.core.shared.exception;

public class ConflictException extends BusinessException {
    public ConflictException(String message) { super("CONFLICT", message); }
}
