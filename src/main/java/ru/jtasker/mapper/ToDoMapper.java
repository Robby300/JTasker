package ru.jtasker.mapper;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.ToDo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Component
public class ToDoMapper {

    public ToDo toModel(ResultSet resultSet) throws SQLException {
        ToDo todo = new ToDo();

            todo.setId(resultSet.getLong("id"));
            todo.setUserId(resultSet.getLong("user_id"));
            todo.setName(resultSet.getString("name"));
            todo.setDescription(resultSet.getString("description"));
            todo.setCreatedOn(LocalDateTime.parse(resultSet.getString("created_on")));
            todo.setDeadline(LocalDateTime.parse(resultSet.getString("deadline")));
            todo.setDone(resultSet.getBoolean("is_done"));
            todo.setNotified(resultSet.getBoolean("is_notified"));

        return todo;
    }
}
