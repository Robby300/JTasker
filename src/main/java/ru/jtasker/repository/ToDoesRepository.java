package ru.jtasker.repository;

import ru.jtasker.domain.ToDo;
import ru.jtasker.domain.User;

import java.util.List;

public interface ToDoesRepository {
    List<ToDo> findAll();

    void save(ToDo toDo);

    List<ToDo> findByUser();

    List<ToDo> findByUserNotDone();
}
