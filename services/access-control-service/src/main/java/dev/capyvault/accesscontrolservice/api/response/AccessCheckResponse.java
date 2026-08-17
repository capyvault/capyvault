package dev.capyvault.accesscontrolservice.api.response;

import dev.capyvault.accesscontrolservice.domain.AccessDecision;
import dev.capyvault.accesscontrolservice.domain.Decision;

public record AccessCheckResponse(
        boolean allowed,
        Decision decision,
        String reason
) {
    public static AccessCheckResponse from(AccessDecision accessDecision) {
        return new AccessCheckResponse(
                accessDecision.allowed(),
                accessDecision.decision(),
                accessDecision.reason()
        );
    }
}