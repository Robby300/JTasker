package ru.jtasker.notifier;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.ToDo;
import ru.jtasker.repository.ToDoesRepository;
import ru.jtasker.repository.UsersRepository;
import ru.jtasker.ui.UserInterface;

import java.time.LocalDateTime;
import java.util.List;
import java.util.SortedMap;

@Component
public class Notifier extends Thread {
    final private UsersRepository usersRepository;
    final private ToDoesRepository toDoesRepository;
    final private UserInterface userInterface;

    public Notifier(UsersRepository usersRepository, ToDoesRepository toDoesRepository1, UserInterface userInterface) {
        this.usersRepository = usersRepository;
        this.toDoesRepository = toDoesRepository1;
        this.userInterface = userInterface;

        setDaemon(true);
    }

    @Override
    public void run() {
        System.err.println("Нотифайер запущен");
        while (true) {

            if (usersRepository.getCurrentUser() != null) {
                System.err.println("Нотифайер заметил логин");
                long userId = usersRepository.getCurrentUser().getId();
                List<ToDo> toDos = toDoesRepository.findAllNotFinishedTasksByUserId(userId);
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



