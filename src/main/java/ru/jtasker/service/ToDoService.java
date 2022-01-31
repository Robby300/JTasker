package ru.jtasker.service;

import org.springframework.stereotype.Service;
import ru.jtasker.domain.ToDo;

import java.sql.SQLException;
import java.util.List;

@Service
public interface ToDoService {
    void saveToDo(ToDo toDo) throws SQLException;
    void deleteToDo(long id);
    void toDoDone(long id);
    void setToDoNotified(long id);
    void editToDo(ToDo currentToDo);
    List<ToDo> findAllNotFinishedTasksByUserId(long userId);
    List<ToDo> findAllNotFinishedAndNotNotifiedTasksByUserId(long userId);
    List<ToDo> findAllFinishedTasksByUserId(long userId);
    List<ToDo> showInnersToDo(long id);
    ToDo findByIdAndUserId(long todoId, long userId);
}
