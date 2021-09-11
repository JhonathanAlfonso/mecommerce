DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;

CREATE TABLE roles (
    id BIGINT IDENTITY(1,1) NOT NULL,
    name varchar(30) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE users_roles (
   user_id BIGINT NOT NULL,
   role_id BIGINT NOT NULL,
   PRIMARY KEY (user_id, role_id)
);

INSERT INTO roles (name) VALUES ('ADMIN');
INSERT INTO roles (name) VALUES ('USER');
