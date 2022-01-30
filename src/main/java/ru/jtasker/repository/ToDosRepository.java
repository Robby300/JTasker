package ru.jtasker.repository;

import ru.jtasker.domain.ToDo;

import java.sql.SQLException;
import java.util.List;

public interface ToDosRepository {

    ToDo save(ToDo toDo) throws SQLException;

    List<ToDo> findAllNotFinishedTasksByUserId(Long userId);

    List<ToDo> findAllFinishedTasksByUserId(Long userId);

    ToDo findByIdAndUserId(Long toDoId, Long userId);

    void toDoDone(long id);

    void deleteToDo(long id);

    ToDo editToDo(ToDo toDo);

    List<ToDo> showInnersToDo(long id);
}
