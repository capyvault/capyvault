package dev.capyvault.identityservice.api.controller;

import dev.capyvault.identityservice.application.exception.ApiError;
import dev.capyvault.identityservice.application.exception.EmailAlreadyExistsException;
import dev.capyvault.identityservice.application.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(
            EmailAlreadyExistsException.class
    )
    public ResponseEntity<ApiError> handleEmailExists(

            EmailAlreadyExistsException exception,

            HttpServletRequest request

    ) {

        return build(
                HttpStatus.CONFLICT,
                exception.getMessage(),
                request
        );
    }

    @ExceptionHandler(
            UserNotFoundException.class
    )
    public ResponseEntity<ApiError> handleNotFound(

            UserNotFoundException exception,

            HttpServletRequest request

    ) {

        return build(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request
        );
    }

    @ExceptionHandler(
            BadCredentialsException.class
    )
    public ResponseEntity<ApiError> handleBadCredentials(

            BadCredentialsException exception,

            HttpServletRequest request

    ) {

        return build(
                HttpStatus.UNAUTHORIZED,
                exception.getMessage(),
                request
        );
    }

    @ExceptionHandler(
            MethodArgumentNotValidException.class
    )
    public ResponseEntity<ApiError> handleValidation(

            MethodArgumentNotValidException exception,

            HttpServletRequest request

    ) {

        String message =
                exception
                        .getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .findFirst()
                        .map(error ->
                                error.getField()
                                        + ": "
                                        + error.getDefaultMessage()
                        )
                        .orElse(
                                "Validation failed."
                        );

        return build(
                HttpStatus.UNPROCESSABLE_CONTENT,
                message,
                request
        );
    }

    @ExceptionHandler(
            IllegalArgumentException.class
    )
    public ResponseEntity<ApiError> handleIllegalArgument(

            IllegalArgumentException exception,

            HttpServletRequest request

    ) {

        return build(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request
        );
    }

    private ResponseEntity<ApiError> build(

            HttpStatus status,

            String message,

            HttpServletRequest request

    ) {

        ApiError error =
                new ApiError(
                        Instant.now(),
                        status.value(),
                        status.name(),
                        message,
                        request.getRequestURI()
                );

        return ResponseEntity
                .status(status)
                .body(error);
    }
}