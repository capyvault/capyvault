# CapyVault Secret Service

MVP service that owns encrypted secrets and secret versions.

## Run

Start project-service first on port 8082, then:

```bash
mvn spring-boot:run
```

Secret service runs on:

```text
http://localhost:8083
```

H2 console:

```text
http://localhost:8083/h2-console
```

JDBC URL:

```text
jdbc:h2:mem:secret_service_db
```

## Main APIs

Create secret:

```http
POST /api/v1/secrets
```

Body:

```json
{
  "projectUuid": "project uuid from project-service",
  "environmentUuid": "environment uuid from project-service",
  "key": "DATABASE_PASSWORD",
  "value": "my-password",
  "description": "Database password",
  "actorUuid": "11111111-1111-1111-1111-111111111111"
}
```

List metadata only:

```http
GET /api/v1/secrets?projectUuid=...&environmentUuid=...
```

Read actual value:

```http
GET /api/v1/secrets/{secretUuid}/value
```

Rotate secret:

```http
POST /api/v1/secrets/{secretUuid}/versions
```

## Service relationship

`secret-service` stores only `projectUuid` and `environmentUuid`. It validates them by calling:

```http
GET project-service/internal/v1/projects/{projectUuid}/environments/{environmentUuid}
```

No shared database. No cross-service JPA relation.
