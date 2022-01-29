package ru.jtasker.repository.db;

import java.sql.SQLException;

public class UserTable extends BaseTable implements TableOperations {

    public UserTable() throws SQLException {
        super("user");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGSERIAL PRIMARY KEY, name VARCHAR(255), lastname VARCHAR(255)," +
                " email VARCHAR(255))", "Создана таблица " + tableName);
    }

    @Override
    public void createForeignKeys() throws SQLException {
    }
}

