package dev.capyvault.accesscontrolservice.api;

import dev.capyvault.accesscontrolservice.api.request.AccessCheckRequest;
import dev.capyvault.accesscontrolservice.api.response.AccessCheckResponse;
import dev.capyvault.accesscontrolservice.application.command.CheckAccessCommand;
import dev.capyvault.accesscontrolservice.application.handler.CheckAccessHandler;
import dev.capyvault.accesscontrolservice.domain.AccessDecision;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/internal/access-checks")
public class AccessCheckController {

    private final CheckAccessHandler checkAccessHandler;

    public AccessCheckController(CheckAccessHandler checkAccessHandler) {
        this.checkAccessHandler = checkAccessHandler;
    }

    @PostMapping
    public AccessCheckResponse check(@Valid @RequestBody AccessCheckRequest request) {
        AccessDecision decision = checkAccessHandler.handle(
                new CheckAccessCommand(
                        request.principalId(),
                        request.principalType(),
                        request.projectId(),
                        request.environment(),
                        request.action()
                )
        );

        return AccessCheckResponse.from(decision);
    }
}