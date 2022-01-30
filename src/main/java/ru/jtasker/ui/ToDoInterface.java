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
        System.out.println("1. Создать задачу\n2. Посмотреть незавершённые задачи" +
                "\n3. Посмотреть завершённые задачи\n4. Открыть меню задачи по ID.\n5. Выход из учетной записи.");
    }

    public void printToDoInterface() {
        System.out.println("1. Изменить задачу\n2. Добавить вложенную задачу\n3. Удалить задачу" +
                "\n4. Отметить выполнение\n5. В предыдущее меню.");
    }

    public void insertCommandForCurrentToDo(Scanner scanner, ToDo currentToDo) throws SQLException {
        String command = scanner.nextLine();
        long userId = usersRepository.getCurrentUser().getId();
        switch (command) {
            case "1":
                System.out.println("Редактор задачи:");
                editToDo(scanner, currentToDo);
                break;
            case "2":
                System.out.println("Создание вложенной задачи:");
                createAndSaveToDo(scanner, currentToDo.getId());
                break;
            case "3":
                System.out.println("Удалить задачу:");
                toDoesRepository.deleteToDo(currentToDo.getId());
                break;
            case "4":
                System.out.println("Отметка о выполнении");
                toDoesRepository.toDoDone(currentToDo.getId());
                System.out.println(currentToDo);
                printToDoInterface();
                break;
            case "5":
                System.out.println("Возврат в предыдущее меню");
                printToDoListInterface();
                insertCommandForRegisteredUser(scanner);
                break;
            default:
                System.out.println("Введите число от 1 до 5х");
                break;
        }
    }

    public void insertCommandForRegisteredUser(Scanner scanner) throws SQLException {
        String command = scanner.nextLine();
        long userId = usersRepository.getCurrentUser().getId();
        switch (command) {
            case "1":
                System.out.println("Создание задачи:");
                createAndSaveToDo(scanner, 0);
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
                System.out.println("Введите ID Задачи");
                ToDo currentToDo = toDoesRepository.findByIdAndUserId(Long.parseLong(scanner.nextLine()),
                        usersRepository.getCurrentUser().getId());
                System.out.println(currentToDo);
                printToDoInterface();
                insertCommandForCurrentToDo(scanner, currentToDo);
                break;
            case "5":
                System.out.println("Возврат в основное меню");
                usersRepository.setCurrentUserToNull();
                userInterface.printUserInterface();
                userInterface.insertCommand(scanner);
                break;
            default:
                System.out.println("Введите число от 1 до 5х");
                break;
        }
    }

    private void createAndSaveToDo(Scanner scanner, long parentId) throws SQLException {
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
                .parentToDoId(parentId)
                .description(description)
                .createdOn(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .isDone(false)
                .deadline(deadline)
                .build();

        toDoesRepository.save(toDo);
        System.out.println("Задача успешно сохранена!");
    }

    private void editToDo(Scanner scanner, ToDo toDo) throws SQLException {
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

        ToDo updatedToDo = new ToDo.Builder()
                .id(toDo.getId())
                .name(name)
                .userId(userId)
                .parentToDoId(toDoesRepository.findByIdAndUserId(toDo.getId(), userId).getParentToDoId())
                .description(description)
                .createdOn(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                .isDone(toDo.isDone())
                .deadline(deadline)
                .build();

        toDoesRepository.save(toDo);
        System.out.println("Задача успешно изменена");
    }
}

