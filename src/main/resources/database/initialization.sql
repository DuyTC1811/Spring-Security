DROP TABLE IF EXISTS USER_ROLE;

DROP TABLE IF EXISTS ROLE;

DROP TABLE IF EXISTS "USER";

CREATE TABLE role
(
    role_id     VARCHAR(36)  NOT NULL,
    title       VARCHAR(30)  NOT NULL,
    slug        VARCHAR(15)  NOT NULL,
    active      varchar(10)  NOT NULL DEFAULT 'ACTIVE',
    description varchar(200) NULL,
    created_at  DATE         NOT NULL,
    updated_at  DATE         NULL     DEFAULT NULL,
    PRIMARY KEY (role_id)
);
CREATE UNIQUE INDEX "index_slug" ON role (slug ASC);

CREATE SEQUENCE IF NOT EXISTS auto_user_code;
CREATE TABLE "user"
(
    user_id       VARCHAR(36) NOT NULL,
    user_code     VARCHAR(10) NOT NULL UNIQUE DEFAULT CONCAT('NV', LPAD(NEXTVAL('auto_user_code')::text, 6, '0')),
    username      VARCHAR(10) NULL,
    first_name    VARCHAR(10) NULL,
    last_name     VARCHAR(10) NULL,
    "name"        VARCHAR(10) NULL,
    mobile        VARCHAR(15) NULL,
    email         VARCHAR(50) NULL,
    cccd          VARCHAR(12) NULL,
    active        varchar(10) NOT NULL        DEFAULT 'ACTIVE',
    password      VARCHAR(60) NOT NULL,
    registered_at DATE        NOT NULL,
    lastLogin     DATE        NULL            DEFAULT NULL,
    PRIMARY KEY (user_id)
);

CREATE UNIQUE INDEX "index_mobile" ON "user" (mobile ASC);
CREATE UNIQUE INDEX "index_mail" ON "user" (email ASC);

CREATE TABLE user_role
(
    user_id VARCHAR(36) NOT NULL,
    role_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fku_role FOREIGN KEY (role_id)
        REFERENCES role (role_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fkr_user FOREIGN KEY (user_id)
        REFERENCES "user" (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO role (ROLE_ID, TITLE, SLUG, ACTIVE, DESCRIPTION, CREATED_AT)
VALUES ('8cf8a4ad-746b-43fd-9c3e-87681a55380e', 'Cấp ADMIN', 'ROLE_ADMIN', 'ACTIVE', 'Thông tin mô tả ADMIN',
        '2023-02-25'),
       ('0124f4ad-ea1e-47f3-8a23-b53f0c21c90b', 'Cấp User', 'ROLE_USER', 'ACTIVE', 'Thông tin mô tả USER',
        '2023-02-25'),
       ('469bbb21-79ce-43a0-b8aa-9ee181572109', 'Cấp Manage', 'ROLE_MANAGER', 'ACTIVE', 'Thông tin mô tả MANAGER',
        '2023-02-25');