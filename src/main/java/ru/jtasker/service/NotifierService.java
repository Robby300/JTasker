package ru.jtasker.service;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.ToDo;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NotifierService {
    final private UserService userService;
    final private ToDoService toDoService;

    public NotifierService(UserService userService, ToDoService toDoService) {
        this.userService = userService;
        this.toDoService = toDoService;
    }

    public void notifyBeforeOneHour() {
        if (userService.getCurrentUser() != null) {
            long userId = userService.getCurrentUser().getId();
            List<ToDo> toDos = toDoService.findAllNotFinishedAndNotNotifiedTasksByUserId(userId);
            for (ToDo todo : toDos) {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime notifyTime = todo.getDeadline().minusHours(1);
                if (now.isAfter(notifyTime)) {
                    System.err.println("Дедлайн вашей задачи наступит в течение часа!");
                    System.err.println(todo);
                    toDoService.setToDoNotified(todo.getId());
                }
            }
        }
    }
}



