package ru.jtasker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.jtasker.domain.ToDo;
import ru.jtasker.repository.db.ConnectionToDb;

import java.sql.SQLException;
import java.util.List;

public interface ToDoesRepository {

    ToDo save(ToDo toDo) throws SQLException;

    List<ToDo> findAllByUserId(Long userId);

    List<ToDo> findAllNotFinishedTasksByUserId(Long userId);

    List<ToDo> findAllFinishedTasksByUserId(Long userId);
}
