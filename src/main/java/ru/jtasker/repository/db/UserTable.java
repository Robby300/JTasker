package ru.jtasker.repository.db;

import java.sql.SQLException;

public class UserTable extends BaseTable implements TableOperations {

    public UserTable() throws SQLException {
        super("user");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE user(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(255) NOT NULL," +
                "pass VARCHAR(255) NOT NULL," +
                "email VARCHAR(255) NOT NULL)", "Создана таблица " + tableName);
    }

    @Override
    public void createForeignKeys() throws SQLException {
    }
}

