package ru.jtasker.repository.db;

import java.sql.SQLException;

public interface TableOperations {
    void createTable() throws SQLException; // создание таблицы

    void insert(String sql) throws SQLException;
}
