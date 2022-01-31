package ru.jtasker.service;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.ToDo;
import ru.jtasker.repository.ToDosRepository;
import ru.jtasker.repository.UsersRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

@Component
public class ToDoServiceImpl implements ToDoService {

    private final ToDosRepository toDosRepository;
    private final UsersRepository usersRepository;

    public ToDoServiceImpl(ToDosRepository toDosRepository, UsersRepository usersRepository) {
        this.toDosRepository = toDosRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public List<ToDo> showInnersToDo(long id) {
        return toDosRepository.showInnersToDo(id);
    }

    @Override
    public ToDo findByIdAndUserId(long todoId, long userId) {
        return toDosRepository.findByIdAndUserId(todoId, userId);
    }

    @Override
    public void deleteToDo(long id) {
        toDosRepository.deleteToDo(id);
    }

    @Override
    public void toDoDone(long id) {
        toDosRepository.toDoDone(id);
    }

    @Override
    public void setToDoNotified(long id) {
        toDosRepository.setToDoNotified(id);
    }

    @Override
    public void editToDo(Scanner scanner, ToDo toDo) {
        System.out.println("Вы выбрали редактирование задачи:");
        System.out.println("Введите новое наименование задачи:");
        Object[] toDoParts = getToDoParts(scanner);
        toDo.setName((String) toDoParts[0]);
        toDo.setDescription((String) toDoParts[1]);
        toDo.setDeadline((LocalDateTime) toDoParts[2]);
        System.out.println(toDosRepository.editToDo(toDo));
    }

    @Override
    public void createAndSaveToDo(Scanner scanner, long parentId) {
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
        try {
            toDosRepository.save(toDo);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка сохранения задачи.");
        }
        System.out.println("Задача успешно сохранена!");
    }

    @Override
    public List<ToDo> findAllNotFinishedTasksByUserId(long userId) {
        return toDosRepository.findAllNotFinishedTasksByUserId(userId);
    }

    @Override
    public List<ToDo> findAllNotFinishedAndNotNotifiedTasksByUserId(long userId) {
        return toDosRepository.findAllNotFinishedAndNotNotifiedTasksByUserId(userId);
    }

    @Override
    public List<ToDo> findAllFinishedTasksByUserId(long userId) {
        return toDosRepository.findAllFinishedTasksByUserId(userId);
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
