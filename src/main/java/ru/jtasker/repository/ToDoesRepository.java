package ru.jtasker.repository;

import ru.jtasker.domain.ToDo;

import java.util.List;

public interface ToDoesRepository {

    ToDo save(ToDo toDo);

    List<ToDo> findAllByUserId(Long userId);

    List<ToDo> findAllNotFinishedTasksByUserId(Long userId);

    List<ToDo> findAllFinishedTasksByUserId(Long userId);
}
