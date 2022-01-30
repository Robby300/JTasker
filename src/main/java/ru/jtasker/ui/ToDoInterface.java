package ru.jtasker.ui;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.ToDo;
import ru.jtasker.service.ToDoService;
import ru.jtasker.service.UserService;

import java.sql.SQLException;
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
                toDoService.editToDo(scanner, currentToDo);
            }
            case "2" -> {
                System.out.println("Создание вложенной задачи:");
                System.out.println(currentToDo);
                toDoService.createAndSaveToDo(scanner, currentToDo.getId());
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
                System.out.println(currentToDo);
            }
            case "6" -> {
                System.out.println("Возврат в предыдущее меню");
                printToDoListInterface();
                insertCommandForRegisteredUser(scanner);
            }
            default -> System.out.println("Введите число от 1 до 6");
        }
    }

    public void insertCommandForRegisteredUser(Scanner scanner) throws SQLException {
        String command = scanner.nextLine();
        long userId = userService.getCurrentUser().getId();
        switch (command) {
            case "1" -> {
                System.out.println("Создание задачи:");
                toDoService.createAndSaveToDo(scanner, 0);
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
                System.out.println("Введите ID Задачи");
                ToDo currentToDo = toDoService.findByIdAndUserId(Long.parseLong(scanner.nextLine()), userId);
                System.out.println(currentToDo);
                printToDoInterface();
                insertCommandForCurrentToDo(scanner, currentToDo);
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
}

