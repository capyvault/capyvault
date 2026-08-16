package dev.capyvault.identityservice.api.response;

public record LoginResponse(

        String accessToken,

        String tokenType,

        long expiresIn

) {
}
