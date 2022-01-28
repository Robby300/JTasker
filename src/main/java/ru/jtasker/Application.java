package ru.jtasker;



import javax.sql.DataSource;
import javax.swing.*;

public class Application {
    DataSource dataSource = new DataSource()

    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
