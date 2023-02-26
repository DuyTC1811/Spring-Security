DROP TABLE IF EXISTS "userInfo";

DROP TABLE IF EXISTS role_permission;

DROP TABLE IF EXISTS role;

DROP TABLE IF EXISTS permission;

CREATE TABLE role
(
    role_id       VARCHAR(36)  NOT NULL,
    title         VARCHAR(30)  NOT NULL,
    slug          VARCHAR(10)  NOT NULL,
    active        varchar(10)  NOT NULL DEFAULT 'ACTIVE',
    description   varchar(200) NULL,
    created_at    DATE         NOT NULL,
    updated_at    DATE         NULL     DEFAULT NULL,
    PRIMARY KEY (role_id)
);
CREATE UNIQUE INDEX "index_slug" ON role (slug ASC);

CREATE TABLE permission
(
    permission_id VARCHAR(36)  NOT NULL,
    title         VARCHAR(30)  NOT NULL,
    slug          VARCHAR(10)  NOT NULL,
    description   varchar(200) NULL,
    active        varchar(10)  NOT NULL DEFAULT 'ACTIVE',
    created_at    DATE         NOT NULL,
    updated_at    DATE         NULL     DEFAULT NULL,
    PRIMARY KEY (permission_id)
);
CREATE UNIQUE INDEX "index_per_slug" ON permission (slug ASC);

CREATE TABLE "user"
(
    user_id       VARCHAR(36) NOT NULL,
    username      VARCHAR(10) NULL,
    first_name    VARCHAR(10) NULL,
    last_name     VARCHAR(10) NULL,
    "name"        VARCHAR(10) NULL,
    mobile        VARCHAR(15) NULL,
    email         VARCHAR(50) NULL,
    cccd          VARCHAR(12) NULL,
    permission_id VARCHAR(36)  NOT NULL,
    active        varchar(10) NOT NULL DEFAULT 'ACTIVE',
    password_hash VARCHAR(60) NOT NULL,
    registered_at DATE        NOT NULL,
    lastLogin     DATE        NULL     DEFAULT NULL,
    PRIMARY KEY (user_id),
    CONSTRAINT fkr_user FOREIGN KEY (permission_id) REFERENCES permission (permission_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE UNIQUE INDEX "index_mobile" ON "user" (mobile ASC);
CREATE UNIQUE INDEX "index_mail" ON "user" (email ASC);

CREATE TABLE user_role
(
    user_id VARCHAR(36) NOT NULL,
    role_id VARCHAR(36) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fku_role FOREIGN KEY (role_id) REFERENCES role (role_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT fkr_user FOREIGN KEY (user_id) REFERENCES "user" (user_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

INSERT INTO role (role_id, title, slug, active, description, created_at)
VALUES ('8cf8a4ad-746b-43fd-9c3e-87681a55380e', 'Cấp ADMIN', 'ADMIN', 'ACTIVE', 'Thông tin mô tả ADMIN', '2023-02-25'),
       ('0124f4ad-ea1e-47f3-8a23-b53f0c21c90b', 'Cấp User', 'USER', 'ACTIVE', 'Thông tin mô tả USER', '2023-02-25'),
       ('469bbb21-79ce-43a0-b8aa-9ee181572109', 'Cấp Manage', 'MANAGER', 'ACTIVE', 'Thông tin mô tả MANAGER',
        '2023-02-25');

INSERT INTO permission (permission_id, title, slug, description, active, created_at)
VALUES ('e3628c46-3142-4b36-ab9b-28dcfdf5f8e4', 'Quyền đọc', 'READ', 'Chỉ đọc', 'ACTIVE', '2023-02-26'),
       ('878d896a-b395-4962-9fb3-1a087f0cbe25', 'Quyền nghi', 'WRITE', 'Chỉ ghi', 'ACTIVE', '2023-02-26'),
       ('8deec71b-03e3-4172-b909-3b5eda6828ae', 'Quyền đọc lẫn ghi', 'READ_WRITE', 'cả đọc lần ghi', 'ACTIVE',
        '2023-02-26');