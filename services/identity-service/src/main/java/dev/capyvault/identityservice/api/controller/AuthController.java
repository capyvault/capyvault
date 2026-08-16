package dev.capyvault.identityservice.api.controller;

import dev.capyvault.identityservice.api.request.LoginRequest;
import dev.capyvault.identityservice.api.response.LoginResponse;
import dev.capyvault.identityservice.application.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(

            @Valid
            @RequestBody
            LoginRequest request

    ) {

        return ResponseEntity.ok(
                authService.login(request)
        );
    }
}
