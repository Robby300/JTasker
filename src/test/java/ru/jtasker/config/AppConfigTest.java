package ru.jtasker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Configuration
@ComponentScan("ru.jtasker")
public class AppConfigTest {

    private static final String DB_URL = "jdbc:sqlite:memory:testdb";

    @Bean
    public Connection connection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Needed to run @Sql scripts in tests
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(DB_URL);
        return ds;
    }
}
