package ru.jtasker;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.jtasker.config.AppConfig;
import ru.jtasker.repository.ToDoesRepositoryImpl;
import ru.jtasker.repository.db.ToDoTable;
import ru.jtasker.repository.db.UserTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

@Component
public class Application {
    ToDoesRepositoryImpl toDoesRepository;
    public static final String DB_URL = "jdbc:h2:mem:/";
    // Таблицы
    UserTable userTable;
    ToDoTable toDoTable;
    //ToDoesRepositoryImpl toDoesRepository;

    public Application(ToDoesRepositoryImpl toDoesRepository) throws SQLException {
        userTable = new UserTable();
        toDoTable = new ToDoTable();
        this.toDoesRepository = toDoesRepository;
    }

    // соединение с БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Создание всех таблиц
    public void createTablesAndForeignKeys() throws SQLException {
        userTable.createTable();
        toDoTable.createTable();
    }

    public void printConsoleInterface() {
        System.out.println("1. Создать таску\n2. Посмотреть незавершённые таски\n3. Посмотреть завершённые таски\n4. Выход");
    }

    public void insertDefaultUser() throws SQLException {
        userTable.insert("INSERT INTO Users(id, username, password, email) VALUES (1, 'Ivan', '123', 'Ivan@mail.ru')");
    }

    public void insertCommandForRegisteredUser(Scanner scanner) {
        String command = scanner.nextLine();
        switch (command) {
            case "1":
                System.out.println("Вы выбрали создание задачи");
                break;
            case "2":
                System.out.println("Ваши незавершённые задачи:");
                break;
            case "3":
                System.out.println("Ваши завершённые задачи:");
                break;
            case "4":
                System.out.println("GoodBye...");
                break;
            default:
                System.out.println("Введите число от 1 до 4х");
                break;
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Application application = context.getBean(Application.class);
        application.createTablesAndForeignKeys();
        application.insertDefaultUser();
        System.out.println("Hello Ivan, tell me about your tasks");
        while (true) {
            application.printConsoleInterface();
            application.insertCommandForRegisteredUser(scanner);
        }
    }

}


