CREATE TABLE users
(
    id            BIGSERIAL PRIMARY KEY,

    uuid          UUID         NOT NULL UNIQUE,

    username      VARCHAR(100) NOT NULL UNIQUE,

    email         VARCHAR(255) NOT NULL UNIQUE,

    password_hash VARCHAR(255) NOT NULL,

    status        VARCHAR(30)  NOT NULL,

    created_at    TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP,

    updated_at    TIMESTAMPTZ  NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_users_email
    ON users (email);

CREATE INDEX idx_users_username
    ON users (username);

CREATE INDEX idx_users_uuid
    ON users (uuid);