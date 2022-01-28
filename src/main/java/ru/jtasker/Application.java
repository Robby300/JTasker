package ru.jtasker;

import org.springframework.boot.SpringApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class Application {
    DataSource dataSource = new DataSource();

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
