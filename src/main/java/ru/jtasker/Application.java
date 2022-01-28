package ru.jtasker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {
    DataSource dataSource = new DriverManagerDataSource();
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
