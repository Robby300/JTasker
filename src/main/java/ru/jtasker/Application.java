package ru.jtasker;


import org.h2.jdbc.JdbcConnection;
import ru.jtasker.domain.ToDo;
import ru.jtasker.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Application {

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.
                    getConnection("jdbc:h2:mem:test", "sa", "");
            System.out.println(connection.getSchema());
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        User user = new User.Builder().id(1L).userName("Vasya").email("Sobaka@mail.ru").password("123").build();
        ToDo toDo = new ToDo.Builder()
                .id(2L)
                .userId(1L)
                .name("выспасться")
                .description("Необходиммо выспаться")
                .createdOn(LocalDateTime.now())
                .deadline(LocalDateTime.MAX)
                .isDone(false)
                .parentToDoId(3).build();
        int i = 0;
    }

}
