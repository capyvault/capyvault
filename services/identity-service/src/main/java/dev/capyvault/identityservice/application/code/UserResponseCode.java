package dev.capyvault.identityservice.application.code;

import dev.capyvault.commonweb.code.ResponseCode;

public enum UserResponseCode
        implements ResponseCode {

    CREATED(
            "USR-0001",
            "User created successfully"
    ),

    UPDATED(
            "USR-0002",
            "User updated successfully"
    ),

    NOT_FOUND(
            "USR-1001",
            "User not found"
    ),

    ALREADY_EXISTS(
            "USR-1002",
            "User already exists"
    );

    private final String code;
    private final String message;

    UserResponseCode(
            String code,
            String message
    ) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}