package ru.jtasker.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.jtasker.domain.ToDo;
import ru.jtasker.repository.db.ToDoTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ToDoesRepositoryImpl implements ToDoesRepository {
    @Autowired
    ToDoTable toDoTable;

    public ToDoesRepositoryImpl(ToDoTable toDoTable) {
        this.toDoTable = toDoTable;
    }

    public ToDoesRepositoryImpl() {
    }

    @Override
    public ToDo save(ToDo toDo) {
        try {
            toDoTable.insert("INSERT INTO todo(name, description, created_on, deadline)" +
                    "VALUES ('" + toDo.getName() + "','" + toDo.getDescription()
                    + "','" + toDo.getCreatedOn() + "','" + toDo.getDeadline() + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDo;
    }

    @Override
    public List<ToDo> findAllByUserId(Long userId) {
        //toDoTable.findAllByUserId(userId);
        return null;
    }

    @Override
    public List<ToDo> findAllNotFinishedTasksByUserId(Long userId) {
        List<ToDo> toDoList = new ArrayList<>();

        return toDoList;
    }

    @Override
    public List<ToDo> findAllFinishedTasksByUserId(Long userId) {
        return null;
    }
}
