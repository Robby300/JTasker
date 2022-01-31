package ru.jtasker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Configuration
@EnableScheduling
@ComponentScan("ru.jtasker")
public class AppConfig {

    private static final String DB_URL = "jdbc:sqlite:memory:testdb";

    private static final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS users " +
            "(id INTEGER PRIMARY KEY, username VARCHAR(255), password VARCHAR(255)," +
            " email VARCHAR(255))";

    private static final String CREATE_TODOS_TABLE = "CREATE TABLE IF NOT EXISTS todos(" +
            "id INTEGER PRIMARY KEY, user_id BIGINT, name VARCHAR(255) NOT NULL," +
            "description VARCHAR(255) NOT NULL, created_on DATETIME, deadline DATETIME," +
            "is_done BOOLEAN, is_notified BOOLEAN, parent_todo BIGINT, FOREIGN KEY (user_id) REFERENCES users(id))";

    @Bean
    public Connection connection() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL);

        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE_USERS_TABLE);
            statement.execute(CREATE_TODOS_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
