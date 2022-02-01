DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS todos;

CREATE TABLE users (
                       "id"       INTEGER PRIMARY KEY,
                       "username" VARCHAR(255),
                       "password" VARCHAR(255)
);

CREATE TABLE todos (
                       "id"          INTEGER PRIMARY KEY,
                       "user_id"     BIGINT,
                       "name"        VARCHAR(255),
                       "description" VARCHAR(1000),
                       "created_on"  TIMESTAMP,
                       "deadline"    TIMESTAMP,
                       "is_done"     BOOLEAN,
                       "is_notified" BOOLEAN,
                       "parent_todo"   BIGINT,
                       FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users(id, username, password) VALUES (1, 'Ivan', '123');

INSERT INTO todos
(id, user_id, name, description, created_on, deadline, is_done, parent_todo)
VALUES
    (1, 1, 'testTask', 'newTestTask', '2022-01-01T23:00', '2022-01-02T01:00', false, 0),
    (2, 1, 'testTask2', 'newTestTas2', '2022-01-01T23:00', '2022-01-02T01:00', false, 1);