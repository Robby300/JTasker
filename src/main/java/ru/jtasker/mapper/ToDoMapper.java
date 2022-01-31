package ru.jtasker.mapper;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.ToDo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class ToDoMapper {

    public ToDo toModel(ResultSet resultSet) {
        ToDo todo = new ToDo();

        try {
            todo.setId(resultSet.getLong("id"));
            todo.setUserId(resultSet.getLong("user_id"));
            todo.setName(resultSet.getString("name"));
            todo.setDescription(resultSet.getString("description"));
            todo.setCreatedOn(LocalDateTime.parse(resultSet.getString("created_on")));
            todo.setDeadline(LocalDateTime.parse(resultSet.getString("deadline")));
            todo.setDone(resultSet.getBoolean("is_done"));
            todo.setDone(resultSet.getBoolean("is_notified"));
            todo.setParentToDoId(resultSet.getLong("parent_todo"));
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка маппера задачи");
        }

        return todo;
    }
}
