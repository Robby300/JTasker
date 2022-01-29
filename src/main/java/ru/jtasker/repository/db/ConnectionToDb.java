package ru.jtasker.repository.db;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConnectionToDb {
    public static final String DB_URL = "jdbc:h2:mem:/";

    public ConnectionToDb() {
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
