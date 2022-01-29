package ru.jtasker;


import ru.jtasker.repository.db.ToDoTable;
import ru.jtasker.repository.db.UserTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    public static final String DB_URL = "jdbc:h2:mem:/home/robert/IdeaProjects/JTasker/db/database";

    // Таблицы
    UserTable userTable;
    ToDoTable toDoTable;

    // соединение с БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // инициализация
    public Application() throws SQLException, ClassNotFoundException {
        userTable = new UserTable();
        toDoTable = new ToDoTable();
    }

    // Создание всех таблиц
    public void createTablesAndForeignKeys() throws SQLException {
        userTable.createTable();
        toDoTable.createTable();
    }

    public void insertDefaultUser() throws SQLException {
        userTable.insert("INSERT INTO Users(id, username, password, email) VALUES (1, 'Ivan', '123', 'Ivan@mail.ru')");
    }

    public static void main(String[] args) {
        try {
            Application application = new Application();
            application.createTablesAndForeignKeys();
            application.insertDefaultUser();
            int i = 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка SQL !");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

