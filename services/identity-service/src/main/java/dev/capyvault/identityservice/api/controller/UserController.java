package dev.capyvault.identityservice.api.controller;

import dev.capyvault.identityservice.api.request.CreateUserRequest;
import dev.capyvault.identityservice.api.response.UserResponse;
import dev.capyvault.identityservice.application.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(
            @Valid
            @RequestBody
            CreateUserRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        userService.create(request)
                );
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> me(
            Authentication authentication
    ) {

        UUID userUuid =
                (UUID) authentication.getPrincipal();

        return ResponseEntity.ok(
                userService.findByUuid(userUuid)
        );
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserResponse> findByUuid(

            @PathVariable
            UUID uuid

    ) {

        return ResponseEntity.ok(
                userService.findByUuid(uuid)
        );
    }
}
