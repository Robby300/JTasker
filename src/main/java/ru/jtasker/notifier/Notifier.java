package ru.jtasker.notifier;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.ToDo;
import ru.jtasker.service.ToDoService;
import ru.jtasker.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class Notifier extends Thread {
    final private UserService userService;
    final private ToDoService toDoService;

    public Notifier(UserService userService, ToDoService toDoService) {
        this.userService = userService;
        this.toDoService = toDoService;

        setDaemon(true);
    }

    @Override
    public void run() {
        System.err.println("Нотифайер запущен");
        while (true) {

            if (userService.getCurrentUser() != null) {
                System.err.println("Нотифайер заметил логин");
                long userId = userService.getCurrentUser().getId();
                List<ToDo> toDos = toDoService.findAllNotFinishedTasksByUserId(userId);
                for (ToDo todo : toDos) {
                    LocalDateTime now = LocalDateTime.now();
                    LocalDateTime before = todo.getDeadline().minusHours(1).minusMinutes(1);
                    LocalDateTime after = todo.getDeadline().minusHours(1).plusMinutes(1);
                    if (now.isAfter(before) && now.isBefore(after)) {
                        System.err.println("Дедлайн вашей задачи через час!");
                        System.out.println(todo);
                    }
                }
            }
        }
    }
}



