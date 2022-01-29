package ru.jtasker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.jtasker.config.AppConfig;
import ru.jtasker.domain.ToDo;
import ru.jtasker.repository.ToDoesRepositoryImpl;
import ru.jtasker.repository.db.ToDoTable;
import ru.jtasker.repository.db.UserTable;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;

@Component
public class Application {
    @Autowired
    UserTable userTable;
    @Autowired
    ToDoTable toDoTable;
    @Autowired
    ToDoesRepositoryImpl toDoesRepository = new ToDoesRepositoryImpl();

    // Создание всех таблиц
    public void createTablesAndForeignKeys() {
        try {
            userTable.createTable();
            toDoTable.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printConsoleInterface() {
        System.out.println("1. Создать таску\n2. Посмотреть незавершённые таски\n3. Посмотреть завершённые таски\n4. Выход");
    }

    public void insertDefaultUser() throws SQLException {
        userTable.insert("INSERT INTO Users(id, username, password, email) VALUES (1, 'Ivan', '123', 'Ivan@mail.ru')");
    }

    public void insertCommandForRegisteredUser(Scanner scanner) throws SQLException {
        String command = scanner.nextLine();
        switch (command) {
            case "1":
                createAndSaveToDo(scanner);
                break;
            case "2":
                System.out.println("Ваши незавершённые задачи:");
                toDoesRepository.findAllNotFinishedTasksByUserId(1L);
                break;
            case "3":
                System.out.println("Ваши завершённые задачи:");
                toDoesRepository.findAllFinishedTasksByUserId(1L);
                break;
            case "4":
                System.out.println("GoodBye...");
                break;
            default:
                System.out.println("Введите число от 1 до 4х");
                break;
        }
    }

    private void createAndSaveToDo(Scanner scanner) throws SQLException {
        System.out.println("Вы выбрали создание задачи:");
        System.out.println("Введите наименование задачи:");
        String name = scanner.nextLine();
        System.out.println("Введите содержание задачи:");

        String description = scanner.nextLine();
        System.out.println("Введите дедлайн задачи:");

        System.out.println("год YYYY:");
        int year = Integer.parseInt(scanner.nextLine());

        System.out.println("месяц mm:");
        int month = Integer.parseInt(scanner.nextLine());

        System.out.println("день месяца dd:");
        int day = Integer.parseInt(scanner.nextLine());

        System.out.println("час hh:");
        int hour = Integer.parseInt(scanner.nextLine());

        System.out.println("минут mm:");
        int minute = Integer.parseInt(scanner.nextLine());

        LocalDateTime deadline = LocalDateTime.of(year, month, day, hour, minute);

        ToDo toDo = new ToDo.Builder()
                .name(name)
                .description(description)
                .createdOn(LocalDateTime.now())
                .isDone(false)
                .deadline(deadline)
                .build();
        toDoesRepository.save(toDo);
        System.out.println("Задача успешно сохранена!");
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


