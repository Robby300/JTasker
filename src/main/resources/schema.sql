CREATE TABLE "users"
(
    "id"      INTEGER PRIMARY KEY,
    "username" VARCHAR(255),
    "pass"     VARCHAR(255),
    "email"    VARCHAR(100)
);

CREATE TABLE "todo"
(
    "id"          BIGSERIAL PRIMARY KEY,
    "user_id"     BIGINT,
    "name"        VARCHAR(255),
    "description" VARCHAR(1000),
    "created_on"  TIMESTAMP,
    "deadline"    TIMESTAMP,
    "is_done"     BOOLEAN,
    parent_todo BIGINT
);

ALTER TABLE "todo"
    ADD FOREIGN KEY ("user_id") REFERENCES "user" ("id");


CREATE TABLE "users"
(
    "id"      BIGSERIAL PRIMARY KEY,
    "username" VARCHAR(255),
    "pass"     VARCHAR(255),
    "email"    VARCHAR(100)
);
INSERT INTO "users"("username", "pass", "email") VALUES ('Ivan', '123', 'Ivan@mail.ru');

select * from "users"