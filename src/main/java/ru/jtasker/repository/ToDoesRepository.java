package ru.jtasker.repository;

import ru.jtasker.domain.ToDo;

import java.util.List;

public interface ToDoesRepository {

    void save(ToDo toDo);

    List<ToDo> findAllByUserId(Long userId);

    List<ToDo> findByUserIdNotDone(Long userId);

    List<ToDo> findByUserIdDone(Long userId);
}
