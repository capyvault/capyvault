access-control-service
- Knows: who can access what project/environment
- Does not store secrets
- Does not own projects
- Used by secret-service before secret read/write/delete
- Supports roles like OWNER, ADMIN, DEVELOPER, VIEWER


new version

- USER / SERVICE_ACCOUNT principal
- PROJECT / ENVIRONMENT scope
- ALLOW / DENY effect
- temporary access with valid_from / valid_until
- created_by tracking
- access decision reason
- audit event publisher port
- project/environment validation before grant