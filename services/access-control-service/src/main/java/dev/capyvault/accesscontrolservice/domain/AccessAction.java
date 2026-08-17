package dev.capyvault.accesscontrolservice.domain;

public enum AccessAction {
    PROJECT_READ,
    PROJECT_UPDATE,
    PROJECT_DELETE,

    SECRET_READ,
    SECRET_CREATE,
    SECRET_UPDATE,
    SECRET_DELETE,

    SECRET_ROTATE,

    MEMBER_READ,
    MEMBER_INVITE,
    MEMBER_UPDATE,
    MEMBER_REMOVE,

    AUDIT_READ
}
