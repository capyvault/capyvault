package dev.capyvault.commonweb.code;

public enum ValidationResponseCode implements ResponseCode {

    VALIDATION_FAILED(
            "VAL-1001",
            "Validation failed"
    ),

    INVALID_REQUEST(
            "VAL-1002",
            "Invalid request"
    ),

    MISSING_PARAMETER(
            "VAL-1003",
            "Required parameter is missing"
    ),

    INVALID_PARAMETER(
            "VAL-1004",
            "Request parameter is invalid"
    );

    private final String code;
    private final String message;

    ValidationResponseCode(
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
