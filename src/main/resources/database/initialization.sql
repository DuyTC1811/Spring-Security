CREATE TABLE role
(
    role_id     UUID,
    title       VARCHAR(30) NOT NULL,
    slug        VARCHAR(10) NOT NULL,
    active      varchar(10) NOT NULL DEFAULT 'ACTIVE',
    description varchar(200) NULL,
    created_at  DATE        NOT NULL,
    updated_at  DATE NULL DEFAULT NULL,
    PRIMARY KEY (role_id)
);
CREATE UNIQUE INDEX "index_slug" ON role (slug ASC);

CREATE TABLE permission
(
    permission_id UUID,
    title         VARCHAR(30) NOT NULL,
    slug          VARCHAR(10) NOT NULL,
    description   varchar(200) NULL,
    active        varchar(10) NOT NULL DEFAULT 'ACTIVE',
    created_at    DATE        NOT NULL,
    updated_at    DATE NULL DEFAULT NULL,
    PRIMARY KEY (permission_id)
);
CREATE UNIQUE INDEX "index_per_slug" ON permission (slug ASC);

CREATE TABLE "userInfo"
(
    user_id       UUID,
    role_id       UUID,
    username      VARCHAR(10) NULL,
    first_name    VARCHAR(10) NULL,
    last_name     VARCHAR(10) NULL,
    "name"        VARCHAR(10) NULL,
    mobile        VARCHAR(15) NULL,
    email         VARCHAR(50) NULL,
    cccd          VARCHAR(12) NULL,
    active        varchar(10) NOT NULL DEFAULT 'ACTIVE',
    password_hash VARCHAR(36) NOT NULL,
    registered_at DATE        NOT NULL,
    lastLogin     DATE NULL DEFAULT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES role (role_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE UNIQUE INDEX "idx_user_role" ON "userInfo" (role_id ASC);
CREATE UNIQUE INDEX "index_mobile" ON "userInfo" (mobile ASC);
CREATE UNIQUE INDEX "index_mail" ON "userInfo" (email ASC);

CREATE TABLE role_permission
(
    permission_id UUID,
    role_id       UUID,
    PRIMARY KEY (permission_id, role_id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES role (role_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fk_permission FOREIGN KEY (permission_id) REFERENCES permission (permission_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO role (role_id, title, slug, active, description, created_at, updated_at)
VALUES ('8cf8a4ad-746b-43fd-9c3e-87681a55380e', 'Cấp ADMIN', 'ADMIN', 'ACTIVE', 'Thông tin mô tả ADMIN', '2023-02-25',
        NULL),
       ('0124f4ad-ea1e-47f3-8a23-b53f0c21c90b', 'Cấp User', 'USER', 'ACTIVE', 'Thông tin mô tả USER', '2023-02-25',
        NULL),
       ('469bbb21-79ce-43a0-b8aa-9ee181572109', 'Cấp Manage', 'MANAGER', 'ACTIVE', 'Thông tin mô tả MANAGER',
        '2023-02-25', NULL);
