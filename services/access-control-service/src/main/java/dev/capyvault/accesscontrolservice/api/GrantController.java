package dev.capyvault.accesscontrolservice.api;

import dev.capyvault.accesscontrolservice.api.request.CreateGrantRequest;
import dev.capyvault.accesscontrolservice.api.request.UpdateGrantRequest;
import dev.capyvault.accesscontrolservice.api.response.GrantResponse;
import dev.capyvault.accesscontrolservice.application.command.CreateGrantCommand;
import dev.capyvault.accesscontrolservice.application.command.UpdateGrantCommand;
import dev.capyvault.accesscontrolservice.application.handler.CreateGrantHandler;
import dev.capyvault.accesscontrolservice.application.handler.DeleteGrantHandler;
import dev.capyvault.accesscontrolservice.application.handler.FindProjectGrantsHandler;
import dev.capyvault.accesscontrolservice.application.handler.UpdateGrantHandler;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/access-grants")
public class GrantController {

    private final CreateGrantHandler createGrantHandler;
    private final UpdateGrantHandler updateGrantHandler;
    private final DeleteGrantHandler deleteGrantHandler;
    private final FindProjectGrantsHandler findProjectGrantsHandler;

    public GrantController(
            CreateGrantHandler createGrantHandler,
            UpdateGrantHandler updateGrantHandler,
            DeleteGrantHandler deleteGrantHandler,
            FindProjectGrantsHandler findProjectGrantsHandler
    ) {
        this.createGrantHandler = createGrantHandler;
        this.updateGrantHandler = updateGrantHandler;
        this.deleteGrantHandler = deleteGrantHandler;
        this.findProjectGrantsHandler = findProjectGrantsHandler;
    }

    @PostMapping
    public GrantResponse create(@Valid @RequestBody CreateGrantRequest request) {
        return GrantResponse.from(
                createGrantHandler.handle(
                        new CreateGrantCommand(
                                request.principalId(),
                                request.principalType(),
                                request.projectId(),
                                request.environment(),
                                request.scopeType(),
                                request.role(),
                                request.effect(),
                                request.validFrom(),
                                request.validUntil(),
                                request.createdBy()
                        )
                )
        );
    }

    @GetMapping("/projects/{projectId}")
    public List<GrantResponse> findByProject(@PathVariable UUID projectId) {
        return findProjectGrantsHandler.handle(projectId)
                .stream()
                .map(GrantResponse::from)
                .toList();
    }

    @PutMapping("/{grantId}")
    public GrantResponse update(
            @PathVariable UUID grantId,
            @Valid @RequestBody UpdateGrantRequest request
    ) {
        return GrantResponse.from(
                updateGrantHandler.handle(
                        new UpdateGrantCommand(
                                grantId,
                                request.role(),
                                request.effect(),
                                request.status(),
                                request.validFrom(),
                                request.validUntil(),
                                request.actorId()
                        )
                )
        );
    }

    @DeleteMapping("/{grantId}")
    public void delete(
            @PathVariable UUID grantId,
            @RequestParam UUID actorId
    ) {
        deleteGrantHandler.handle(grantId, actorId);
    }
}