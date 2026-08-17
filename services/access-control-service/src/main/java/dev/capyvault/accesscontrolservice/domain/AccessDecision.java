package dev.capyvault.accesscontrolservice.domain;

public record AccessDecision(
        boolean allowed,
        Decision decision,
        String reason
) {
    public static AccessDecision allow(String reason) {
        return new AccessDecision(true, Decision.ALLOW, reason);
    }

    public static AccessDecision deny(String reason) {
        return new AccessDecision(false, Decision.DENY, reason);
    }
}