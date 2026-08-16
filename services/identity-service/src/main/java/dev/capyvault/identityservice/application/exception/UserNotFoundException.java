package dev.capyvault.identityservice.application.exception;

public class UserNotFoundException
        extends RuntimeException {

    public UserNotFoundException() {
        super("User not found.");
    }
}
