package ru.jtasker.ui;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.ToDo;
import ru.jtasker.repository.ToDoesRepository;
import ru.jtasker.repository.UsersRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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
        System.out.println("""
                1. Создать задачу
                2. Посмотреть незавершённые задачи
                3. Посмотреть завершённые задачи
                4. Открыть меню задачи по ID.
                5. Выход из учетной записи.""");
    }

    public void printToDoInterface() {
        System.out.println("""
                1. Изменить задачу
                2. Добавить вложенную задачу
                3. Удалить задачу
                4. Отметить выполнение
                5. В предыдущее меню.""");
    }

    public void insertCommandForCurrentToDo(Scanner scanner, ToDo currentToDo) throws SQLException {
        String command = scanner.nextLine();
        switch (command) {
            case "1" -> {
                System.out.println("Редактор задачи:");
                editToDo(scanner, currentToDo);
            }
            case "2" -> {
                System.out.println("Создание вложенной задачи:");
                System.out.println(currentToDo);
                createAndSaveToDo(scanner, currentToDo.getId());
            }
            case "3" -> {
                System.out.println("Удалить задачу:");
                toDoesRepository.deleteToDo(currentToDo.getId());
            }
            case "4" -> {
                System.out.println("Отметка о выполнении");
                toDoesRepository.toDoDone(currentToDo.getId());
                System.out.println(currentToDo);
            }
            case "5" -> {
                System.out.println("Возврат в предыдущее меню");
                printToDoListInterface();
                insertCommandForRegisteredUser(scanner);
            }
            default -> System.out.println("Введите число от 1 до 5х");
        }
    }

    public void insertCommandForRegisteredUser(Scanner scanner) throws SQLException {
        String command = scanner.nextLine();
        long userId = usersRepository.getCurrentUser().getId();
        switch (command) {
            case "1" -> {
                System.out.println("Создание задачи:");
                createAndSaveToDo(scanner, 0);
            }
            case "2" -> {
                System.out.println("Ваши незавершённые задачи:");
                toDoesRepository.findAllNotFinishedTasksByUserId(userId).forEach(System.out::println);
            }
            case "3" -> {
                System.out.println("Ваши завершённые задачи:");
                toDoesRepository.findAllFinishedTasksByUserId(userId).forEach(System.out::println);
            }
            case "4" -> {
                System.out.println("Введите ID Задачи");
                ToDo currentToDo = toDoesRepository.findByIdAndUserId(Long.parseLong(scanner.nextLine()), userId);
                System.out.println(currentToDo);
                printToDoInterface();
                insertCommandForCurrentToDo(scanner, currentToDo);
            }
            case "5" -> {
                System.out.println("Возврат в основное меню");
                usersRepository.setCurrentUserToNull();
                userInterface.printUserInterface();
                userInterface.insertCommand(scanner);
            }
            default -> System.out.println("Введите число от 1 до 5х");
        }
    }

    private void createAndSaveToDo(Scanner scanner, long parentId) throws SQLException {
        long userId = usersRepository.getCurrentUser().getId();
        System.out.println("Вы выбрали создание задачи:");
        Object[] toDoParts = getToDoParts(scanner);
        ToDo toDo = new ToDo.Builder()
                .name((String) toDoParts[0])
                .userId(userId)
                .parentToDoId(parentId)
                .description((String) toDoParts[1])
                .createdOn(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .isDone(false)
                .deadline((LocalDateTime) toDoParts[2])
                .build();
        toDoesRepository.save(toDo);
        System.out.println("Задача успешно сохранена!");
    }

    private void editToDo(Scanner scanner, ToDo toDo) {
        System.out.println("Вы выбрали редактирование задачи:");
        System.out.println("Введите новое наименование задачи:");
        Object[] toDoParts = getToDoParts(scanner);
        toDo.setName((String) toDoParts[0]);
        toDo.setDescription((String) toDoParts[1]);
        toDo.setDeadline((LocalDateTime) toDoParts[2]);
        toDoesRepository.editToDo(toDo);
    }

    public Object[] getToDoParts(Scanner scanner) {
        Object[] toDoParts = new Object[3];
        System.out.println("Введите имя задачи:");
        String name = scanner.nextLine();
        toDoParts[0] = name;
        System.out.println("Введите содержание задачи:");
        String description = scanner.nextLine();
        toDoParts[1] = description;
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
        toDoParts[2] = deadline;
        return toDoParts;
    }
}

