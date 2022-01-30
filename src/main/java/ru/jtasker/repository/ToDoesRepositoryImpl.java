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
    private final UsersRepository usersRepository;

    private static final String INSERT_TODO = "INSERT INTO todos(" +
            "user_id, name, description, created_on, deadline, parent_todo, is_done) VALUES (?, ?, ?, ?, ?, ?, false)";
    private static final String INSERT_TODO_BY_ID = "INSERT INTO todos(" +
            "id, user_id, name, description, created_on, deadline, is_done) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_ALL_BY_USER_ID = "SELECT * FROM todos " +
            "WHERE user_id = ?";
    private static final String FIND_ALL_NOT_FINISHED_BY_USER_ID = "SELECT * FROM todos " +
            "WHERE user_id = ? AND is_done = false";
    private static final String FIND_ALL_FINISHED_BY_USER_ID = "SELECT * FROM todos " +
            "WHERE user_id = ? AND is_done = true";
    private static final String FIND_BY_ID_AND_USER_ID = "SELECT * FROM todos " +
            "WHERE id = ? AND user_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM todos " +
            "WHERE id = ?";
    private static final String SET_TODO_DONE = "UPDATE todos SET is_done = true WHERE id = ?";

    private static final String UPDATE_TODO = "UPDATE todos SET " +
            "name = ?, description = ?, deadline = ? WHERE id = ?";

    public ToDoesRepositoryImpl(Connection connection, ToDoMapper toDoMapper, UsersRepository usersRepository) {
        this.connection = connection;
        this.toDoMapper = toDoMapper;
        this.usersRepository = usersRepository;
    }

    @Override
    public ToDo save(ToDo toDo) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODO)) {
            preparedStatement.setLong(1, usersRepository.getCurrentUser().getId());
            preparedStatement.setString(2, toDo.getName());
            preparedStatement.setString(3, toDo.getDescription());
            preparedStatement.setString(4, toDo.getCreatedOn().toString());
            preparedStatement.setString(5, toDo.getDeadline().toString());
            preparedStatement.setLong(6, toDo.getParentToDoId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDo;
    }

    @Override
    public ToDo editToDo(ToDo toDo) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TODO)) {
            preparedStatement.setString(1, toDo.getName());
            preparedStatement.setString(2, toDo.getDescription());
            preparedStatement.setString(3, toDo.getDeadline().toString());
            preparedStatement.setLong(4, toDo.getId());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка изменения задачи.");
        }
        return toDo;
    }

    @Override
    public List<ToDo> findAllNotFinishedTasksByUserId(Long userId) {
        List<ToDo> todos = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_NOT_FINISHED_BY_USER_ID)) {
            preparedStatement.setString(1, String.valueOf(userId));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                todos.add(toDoMapper.toModel(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (todos.size() == 0) {
            System.out.println("Список задач пуст");
        }
        return todos;
    }

    @Override
    public List<ToDo> findAllFinishedTasksByUserId(Long userId) {
        List<ToDo> todos = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_FINISHED_BY_USER_ID)
        ) {
            preparedStatement.setString(1, String.valueOf(userId));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                todos.add(toDoMapper.toModel(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (todos.size() == 0) {
            System.out.println("Список задач пуст");
        }
        return todos;
    }

    @Override
    public ToDo findByIdAndUserId(Long id, Long userId) {
        ToDo todo = new ToDo();
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_AND_USER_ID)
        ) {
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.setString(2, String.valueOf(userId));
            ResultSet resultSet = preparedStatement.executeQuery();
            todo = toDoMapper.toModel(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Такой задачи нет.");
        }
        return todo;
    }

    @Override
    public void toDoDone(long id) {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(SET_TODO_DONE)
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteToDo(long id) {
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка удаления");
        }
    }
}
