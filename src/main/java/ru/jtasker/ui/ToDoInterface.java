package ru.jtasker.ui;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.ToDo;
import ru.jtasker.service.ToDoService;
import ru.jtasker.service.UserService;

import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

@Component
public class ToDoInterface {

    private final UserService userService;
    private final ToDoService toDoService;
    private final UserInterface userInterface;

    public ToDoInterface(UserService userService, ToDoService toDoService, UserInterface userInterface) {
        this.userService = userService;
        this.toDoService = toDoService;
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
                3. Показать все вложенные незавершенные задачи
                4. Удалить задачу
                5. Отметить выполнение
                6. В предыдущее меню.""");
    }

    public void insertCommandForCurrentToDo(Scanner scanner, ToDo currentToDo) throws SQLException {
        String command = scanner.nextLine();
        switch (command) {
            case "1" -> {
                System.out.println("Редактор задачи:");
                toDoService.editToDo(currentToDo);
                System.out.println(getUpdatedToDo(currentToDo));
            }
            case "2" -> {
                System.out.println("Создание вложенной задачи:");
                System.out.println(currentToDo);
                toDoService.saveToDo(createToDo(scanner, currentToDo.getId()));
            }
            case "3" -> {
                System.out.println("Все вложенные незавершённые задачи:");
                toDoService.showInnersToDo(currentToDo.getId()).forEach(System.out::println);
            }
            case "4" -> {
                System.out.println("Удалить задачу:");
                toDoService.deleteToDo(currentToDo.getId());
            }
            case "5" -> {
                System.out.println("Отметка о выполнении");
                toDoService.toDoDone(currentToDo.getId());
                System.out.println(getUpdatedToDo(currentToDo));
            }
            case "6" -> {
                System.out.println("Возврат в предыдущее меню");
                printToDoListInterface();
                insertCommandForRegisteredUser(scanner);
            }
            default -> System.out.println("Введите число от 1 до 6");
        }
    }

    private ToDo getUpdatedToDo(ToDo currentToDo) {
        return toDoService.findByIdAndUserId(currentToDo.getId(), userService.getCurrentUser().getId());
    }

    public void insertCommandForRegisteredUser(Scanner scanner) throws SQLException {
        String command = scanner.nextLine();
        long userId = userService.getCurrentUser().getId();
        switch (command) {
            case "1" -> {
                System.out.println("Создание задачи:");
                toDoService.saveToDo(createToDo(scanner, 0));
            }
            case "2" -> {
                System.out.println("Ваши незавершённые задачи:");
                toDoService.findAllNotFinishedTasksByUserId(userId).forEach(System.out::println);
            }
            case "3" -> {
                System.out.println("Ваши завершённые задачи:");
                toDoService.findAllFinishedTasksByUserId(userId).forEach(System.out::println);
            }
            case "4" -> {
                openMenuByToDoId(scanner, userId);

            }
            case "5" -> {
                System.out.println("Возврат в основное меню");
                userService.setCurrentUserToNull();
                userInterface.printUserInterface();
                userInterface.insertCommand(scanner);
            }
            default -> System.out.println("Введите число от 1 до 5");
        }
    }

    private void openMenuByToDoId(Scanner scanner, long userId) {
        try {
            System.out.println("Введите ID Задачи");
            ToDo currentToDo = toDoService.findByIdAndUserId(Long.parseLong(scanner.nextLine()), userId);
            System.out.println(currentToDo);
            printToDoInterface();
            insertCommandForCurrentToDo(scanner, currentToDo);
        } catch (Exception e) {
            openMenuByToDoId(scanner, userId);
        }
    }

    public ToDo createToDo(Scanner scanner, long parentId) {
        long userId = userService.getCurrentUser().getId();
        System.out.println("Вы выбрали создание задачи:");
        Object[] toDoParts = createAndGetToDoParts(scanner);
        ToDo toDo = new ToDo.Builder()
                .name((String) toDoParts[0])
                .userId(userId)
                .parentToDoId(parentId)
                .description((String) toDoParts[1])
                .createdOn(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .isDone(false)
                .deadline((LocalDateTime) toDoParts[2])
                .build();
        return toDo;
    }

    public Object[] createAndGetToDoParts(Scanner scanner) {
        Object[] toDoParts = new Object[3];
        System.out.println("Введите имя задачи:");
        String name = scanner.nextLine();
        toDoParts[0] = name;
        System.out.println("Введите содержание задачи:");
        String description = scanner.nextLine();
        toDoParts[1] = description;
        System.out.println("Введите дедлайн задачи:");
        LocalDateTime deadline = getLocalDateTime(scanner);
        toDoParts[2] = deadline;
        return toDoParts;
    }

    private LocalDateTime getLocalDateTime(Scanner scanner) {
        LocalDateTime deadline = null;
        try {
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
            deadline = LocalDateTime.of(year, month, day, hour, minute);
        } catch (NumberFormatException | DateTimeException e) {
            System.err.println("Неверный формат даты, повторите ввод.");
            return getLocalDateTime(scanner);
        }
        return deadline;
    }
}

