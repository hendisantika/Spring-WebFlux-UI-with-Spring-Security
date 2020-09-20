CREATE TABLE user
(
    username varchar_ignorecase(20)  NOT NULL PRIMARY KEY,
    password varchar_ignorecase(100) NOT NULL,
    enabled  boolean                 NOT NULL
);

CREATE TABLE user_role
(
    id       NUMERIC(38)            NOT NULL PRIMARY KEY,
    username varchar_ignorecase(20) NOT NULL,
    role     varchar_ignorecase(50) NOT NULL,
    CONSTRAINT fk_users_roles FOREIGN KEY (username) REFERENCES user (username)
);
CREATE UNIQUE INDEX ix_auth_username ON user_role (username, role);