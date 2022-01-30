package ru.jtasker.repository;

import org.springframework.stereotype.Repository;
import ru.jtasker.domain.ToDo;
import ru.jtasker.mapper.ToDoMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ToDoesRepositoryImpl implements ToDoesRepository {

    private final Connection connection;
    private final ToDoMapper toDoMapper;

    private static final String INSERT_TODO = "INSERT INTO todos(" +
            "user_id, name, description, created_on, deadline, is_done) VALUES (1, ?, ?, ?, ?, false)";

    private static final String FIND_ALL_NOT_FINISHED = "SELECT * FROM todos " +
            "WHERE user_id = ? AND is_done = false";

    public ToDoesRepositoryImpl(Connection connection, ToDoMapper toDoMapper) {
        this.connection = connection;
        this.toDoMapper = toDoMapper;
    }

    @Override
    public ToDo save(ToDo toDo) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODO)) {
            preparedStatement.setString(1, toDo.getName());
            preparedStatement.setString(2, toDo.getDescription());
            preparedStatement.setString(3, toDo.getCreatedOn().toString());
            preparedStatement.setString(4, toDo.getDeadline().toString());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDo;
    }

    @Override
    public List<ToDo> findAllByUserId(Long userId) {
        return null;
    }

    @Override
    public List<ToDo> findAllNotFinishedTasksByUserId(Long userId) {
        List<ToDo> todos = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_NOT_FINISHED)) {
            preparedStatement.setString(1, String.valueOf(userId));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                todos.add(toDoMapper.toModel(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todos;
    }

    @Override
    public List<ToDo> findAllFinishedTasksByUserId(Long userId) {
        return null;
    }
}
