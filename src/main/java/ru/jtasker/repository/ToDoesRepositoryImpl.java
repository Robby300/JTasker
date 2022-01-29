package ru.jtasker.repository;

import org.springframework.stereotype.Repository;
import ru.jtasker.domain.ToDo;

import java.util.List;

@Repository
public class ToDoesRepositoryImpl implements ToDoesRepository {
    @Override
    public ToDo save(ToDo toDo) {
        return null;
    }

    @Override
    public List<ToDo> findAllByUserId(Long userId) {
        return null;
    }

    @Override
    public List<ToDo> findAllNotFinishedTasksByUserId(Long userId) {
        return null;
    }

    @Override
    public List<ToDo> findAllFinishedTasksByUserId(Long userId) {
        return null;
    }
}
