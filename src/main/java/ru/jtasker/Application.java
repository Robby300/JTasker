package ru.jtasker;


import ru.jtasker.domain.ToDo;
import ru.jtasker.domain.User;
import ru.jtasker.repository.db.ToDoTable;
import ru.jtasker.repository.db.UserTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Application {
    public static final String DB_URL = "jdbc:h2:mem:/home/robert/IdeaProjects/JTasker/db/database";
    public static final String DB_Driver = "org.h2.Driver";

    // Таблицы
    UserTable userTable;
    ToDoTable toDoTable;

    // соединение с БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // инициализация
    public Application() throws SQLException, ClassNotFoundException {
        Class.forName(DB_Driver);
        userTable = new UserTable();
        toDoTable = new ToDoTable();
    }

    // Создание всех таблиц и ключей между ними
    public void createTablesAndForeignKeys() throws SQLException {
        userTable.createTable();
        toDoTable.createTable();
        toDoTable.createForeignKeys();
    }

    public static void main(String[] args) {
        try {
            Application application = new Application();
            application.createTablesAndForeignKeys();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

        /*try {
            //Class.forName(DB_Driver);
            Connection connection = DriverManager.getConnection(DB_URL);
            System.out.println("Hooray We are connected!");
            System.out.println(connection.getSchema());
            connection.close();
            System.out.println("Disconnected!");
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println("Ошибка SQL !");
        }

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

         */

