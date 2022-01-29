package ru.jtasker.repository.db;

import java.sql.SQLException;

public class ToDoTable extends BaseTable implements TableOperations {

    public ToDoTable() throws SQLException {
        super("todo");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS todo(" +
                "id BIGSERIAL PRIMARY KEY," +
                "user_id BIGINT," +
                "name VARCHAR(255) NOT NULL," +
                "description VARCHAR(255) NOT NULL," +
                "created_on TIMESTAMP NOT NULL," +
                "deadline TIMESTAMP NOT NULL," +
                "is_done BOOLEAN NOT NULL," +
                "parent_todo BIGINT," +
                "FOREIGN KEY (user_id) REFERENCES users(id))", "Создана таблица " + tableName);
    }

    @Override
    public void insert(String sql) throws SQLException {
    }
}
