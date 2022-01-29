package ru.jtasker.repository.db;

import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class UserTable extends BaseTable implements TableOperations {

    public UserTable() throws SQLException {
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGSERIAL PRIMARY KEY, username VARCHAR(255), password VARCHAR(255)," +
                " email VARCHAR(255))", "Создана таблица users");
    }

    @Override
    public void insert(String sql) throws SQLException {
        super.executeSqlStatement(sql, "Добавлен пользователь через insert");
    }

}

