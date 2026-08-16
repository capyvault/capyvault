package dev.capyvault.identityservice.infrastructure.security;

import dev.capyvault.identityservice.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    private final SecretKey signingKey;

    private final long expiration;

    public JwtService(

            @Value("${security.jwt.secret}")
            String secret,

            @Value("${security.jwt.expiration}")
            long expiration

    ) {

        this.signingKey =
                Keys.hmacShaKeyFor(
                        secret.getBytes()
                );

        this.expiration = expiration;
    }

    public String generateToken(
            User user
    ) {

        Instant now =
                Instant.now();

        Instant expiresAt =
                now.plusMillis(expiration);

        return Jwts.builder()

                .subject(
                        user.getUuid().toString()
                )

                .claim(
                        "username",
                        user.getUsername()
                )

                .claim(
                        "email",
                        user.getEmail()
                )

                .issuedAt(
                        Date.from(now)
                )

                .expiration(
                        Date.from(expiresAt)
                )

                .id(
                        UUID.randomUUID().toString()
                )

                .signWith(signingKey,Jwts.SIG.HS256)

                .compact();
    }

    public String extractSubject(
            String token
    ) {

        return extractClaims(token)
                .getSubject();
    }

    public boolean isTokenValid(
            String token
    ) {

        try {

            Claims claims =
                    extractClaims(token);

            return claims
                    .getExpiration()
                    .after(new Date());

        } catch (Exception exception) {

            return false;
        }
    }

    public long getExpirationSeconds() {
        return expiration / 1000;
    }

    private Claims extractClaims(
            String token
    ) {

        return Jwts.parser()

                .verifyWith(signingKey)

                .build()

                .parseSignedClaims(token)

                .getPayload();
    }
}