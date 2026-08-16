package dev.capyvault.commonweb.exception;

import dev.capyvault.commonweb.code.ResponseCode;
import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final ResponseCode responseCode;

    private final HttpStatus httpStatus;

    public BusinessException(
            ResponseCode responseCode,
            HttpStatus httpStatus
    ) {

        super(responseCode.message());

        this.responseCode = responseCode;
        this.httpStatus = httpStatus;
    }

    public ResponseCode responseCode() {
        return responseCode;
    }

    public HttpStatus httpStatus() {
        return httpStatus;
    }
}
