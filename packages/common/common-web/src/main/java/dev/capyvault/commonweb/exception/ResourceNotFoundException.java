package dev.capyvault.commonweb.exception;

import dev.capyvault.commonweb.code.ResponseCode;
import org.springframework.http.HttpStatus;

public final class ResourceNotFoundException
        extends BusinessException {

    public ResourceNotFoundException(
            ResponseCode responseCode
    ) {
        super(
                responseCode,
                HttpStatus.NOT_FOUND
        );
    }
}