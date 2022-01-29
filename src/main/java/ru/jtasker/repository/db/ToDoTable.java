package ru.jtasker.repository.db;

import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class ToDoTable extends BaseTable implements TableOperations {

    public ToDoTable() throws SQLException {
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS todo(" +
                "id BIGSERIAL PRIMARY KEY," +
                "user_id BIGINT," +
                "name VARCHAR(255) NOT NULL," +
                "description VARCHAR(255) NOT NULL," +
                "created_on TIMESTAMP," +
                "deadline TIMESTAMP," +
                "is_done BOOLEAN," +
                "parent_todo BIGINT," +
                "FOREIGN KEY (user_id) REFERENCES users(id))", "Создана таблица todo");
    }

    @Override
    public void insert(String sql) throws SQLException {
    }

    /*@Override
    public void findAllByUserId(Long userId) throws SQLException {
        super.executeSqlStatement();
    }*/
}
