package ru.jtasker.ui;

import org.springframework.beans.factory.annotation.Autowired;
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

    public ToDoInterface(ToDoesRepository toDoesRepository, UsersRepository usersRepository) {
        this.toDoesRepository = toDoesRepository;
        this.usersRepository = usersRepository;
    }

    public void printToDoInterface() {
        System.out.println("1. Создать таску\n2. Посмотреть незавершённые таски\n3. Посмотреть завершённые таски\n4. Выход");
    }

    public void insertCommandForRegisteredUser(Scanner scanner) throws SQLException {
        String command = scanner.nextLine();

        switch (command) {
            case "1":
                createAndSaveToDo(scanner);
                break;
            case "2":
                System.out.println("Ваши незавершённые задачи:");
                toDoesRepository.findAllNotFinishedTasksByUserId(1L).forEach(System.out::println);
                break;
            case "3":
                System.out.println("Ваши завершённые задачи:");
                toDoesRepository.findAllFinishedTasksByUserId(1L);
                break;
            case "4":
                System.out.println("GoodBye...");
                System.exit(0);
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
                .createdOn(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .isDone(false)
                .deadline(deadline)
                .build();

        toDoesRepository.save(toDo);
        System.out.println("Задача успешно сохранена!");
    }
}

