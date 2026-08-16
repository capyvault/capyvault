package dev.capyvault.commonweb.code;

public enum CommonResponseCode implements ResponseCode {

    SUCCESS(
            "COM-0000",
            "Operation completed successfully"
    ),

    INTERNAL_SERVER_ERROR(
            "SYS-5000",
            "An unexpected system error occurred"
    ),

    DATABASE_ERROR(
            "SYS-5001",
            "Database operation failed"
    ),

    SERVICE_UNAVAILABLE(
            "SYS-5002",
            "Service is temporarily unavailable"
    ),

    REQUEST_TIMEOUT(
            "SYS-5003",
            "Request timed out"
    );

    private final String code;
    private final String message;

    CommonResponseCode(
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