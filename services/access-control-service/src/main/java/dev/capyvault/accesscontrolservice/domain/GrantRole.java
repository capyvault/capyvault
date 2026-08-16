package dev.capyvault.accesscontrolservice.domain;

import java.util.Set;

public enum GrantRole {

    OWNER(Set.of(
            AccessAction.PROJECT_READ,
            AccessAction.PROJECT_UPDATE,
            AccessAction.PROJECT_DELETE,

            AccessAction.SECRET_READ,
            AccessAction.SECRET_CREATE,
            AccessAction.SECRET_UPDATE,
            AccessAction.SECRET_DELETE,
            AccessAction.SECRET_ROTATE,

            AccessAction.MEMBER_READ,
            AccessAction.MEMBER_INVITE,
            AccessAction.MEMBER_UPDATE,
            AccessAction.MEMBER_REMOVE
    )),

    ADMIN(Set.of(
            AccessAction.PROJECT_READ,
            AccessAction.PROJECT_UPDATE,

            AccessAction.SECRET_READ,
            AccessAction.SECRET_CREATE,
            AccessAction.SECRET_UPDATE,
            AccessAction.SECRET_DELETE,
            AccessAction.SECRET_ROTATE,

            AccessAction.MEMBER_READ,
            AccessAction.MEMBER_INVITE,
            AccessAction.MEMBER_UPDATE
    )),

    DEVELOPER(Set.of(
            AccessAction.PROJECT_READ,

            AccessAction.SECRET_READ,
            AccessAction.SECRET_CREATE,
            AccessAction.SECRET_UPDATE
    )),

    VIEWER(Set.of(
            AccessAction.PROJECT_READ,
            AccessAction.SECRET_READ
    ));

    private final Set<AccessAction> allowedActions;

    GrantRole(Set<AccessAction> allowedActions) {
        this.allowedActions = allowedActions;
    }

    public boolean can(AccessAction action) {
        return allowedActions.contains(action);
    }
}
