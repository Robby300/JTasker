package ru.jtasker.ui;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.ToDo;
import ru.jtasker.repository.ToDoesRepository;
import ru.jtasker.repository.UsersRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

@Component
public class ToDoInterface {

    private final ToDoesRepository toDoesRepository;
    private final UsersRepository usersRepository;
    private final UserInterface userInterface;

    public ToDoInterface(ToDoesRepository toDoesRepository, UsersRepository usersRepository, UserInterface userInterface) {
        this.toDoesRepository = toDoesRepository;
        this.usersRepository = usersRepository;
        this.userInterface = userInterface;
    }

    public void printToDoListInterface() {
        System.out.println("1. Создать таску\n2. Посмотреть незавершённые таски" +
                "\n3. Посмотреть завершённые таски\n4. Выход из учетной записи.");
    }

    public void insertCommandForRegisteredUser(Scanner scanner) throws SQLException {
        String command = scanner.nextLine();
        long userId = usersRepository.getCurrentUser().getId();
        switch (command) {
            case "1":
                createAndSaveToDo(scanner);
                break;
            case "2":
                System.out.println("Ваши незавершённые задачи:");
                toDoesRepository.findAllNotFinishedTasksByUserId(userId).forEach(System.out::println);
                break;
            case "3":
                System.out.println("Ваши завершённые задачи:");
                toDoesRepository.findAllFinishedTasksByUserId(userId);
                break;
            case "4":
                System.out.println("Возврат в основное меню");
                usersRepository.setCurrentUserToNull();
                userInterface.printUserInterface();
                userInterface.insertCommand(scanner);
                break;
            default:
                System.out.println("Введите число от 1 до 4х");
                break;
        }
    }

    private void createAndSaveToDo(Scanner scanner) throws SQLException {
        long userId = usersRepository.getCurrentUser().getId();
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
                .userId(userId)
                .description(description)
                .createdOn(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .isDone(false)
                .deadline(deadline)
                .build();

        toDoesRepository.save(toDo);
        System.out.println("Задача успешно сохранена!");
    }
}

