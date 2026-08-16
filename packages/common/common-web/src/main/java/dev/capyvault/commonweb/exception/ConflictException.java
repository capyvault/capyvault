package dev.capyvault.commonweb.exception;

import dev.capyvault.commonweb.code.ResponseCode;
import org.springframework.http.HttpStatus;

public final class ConflictException
        extends BusinessException {

    public ConflictException(
            ResponseCode responseCode
    ) {
        super(
                responseCode,
                HttpStatus.CONFLICT
        );
    }
}
