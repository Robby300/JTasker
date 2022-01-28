package ru.jtasker.repository;

import ru.jtasker.domain.ToDo;

import java.util.List;

public interface ToDoesRepository {

    void save(ToDo toDo);

    List<ToDo> findAllByUserId();

    List<ToDo> findByUserIdNotDone();

    List<ToDo> findByUserIdDone();
}
