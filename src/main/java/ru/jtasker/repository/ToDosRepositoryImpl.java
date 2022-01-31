package ru.jtasker.repository;

import org.springframework.stereotype.Repository;
import ru.jtasker.domain.ToDo;
import ru.jtasker.mapper.ToDoMapper;
import ru.jtasker.service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ToDosRepositoryImpl implements ToDosRepository {
    // SQL
    private static final String INSERT_TODO = "INSERT INTO todos(" +
            "user_id, name, description, created_on, deadline, parent_todo, is_notified,is_done) VALUES (?, ?, ?, ?, ?, ?, false, false)";
    private static final String FIND_ALL_BY_PARENT_TODO_ID = "SELECT * FROM todos " +
            "WHERE parent_todo = ?";
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
            "name = ?, description = ?, deadline = ?, is_notified = false WHERE id = ?";
    private static final String FIND_ALL_NOT_FINISHED_AND_NOT_NOTIFIED_BY_USER_ID = "SELECT * FROM todos " +
            "WHERE user_id = ? AND is_done = false AND is_notified = false";
    private static final String SET_NOTIFIED_TRUE = "UPDATE todos SET is_notified = true WHERE id = ?";

    private final Connection connection;
    private final ToDoMapper toDoMapper;
    private final UserService userService;

    public ToDosRepositoryImpl(Connection connection, ToDoMapper toDoMapper, UserService userService) {
        this.connection = connection;
        this.toDoMapper = toDoMapper;

        this.userService = userService;
    }

    @Override
    public ToDo save(ToDo toDo) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TODO)) {
            preparedStatement.setLong(1, userService.getCurrentUser().getId());
            preparedStatement.setString(2, toDo.getName());
            preparedStatement.setString(3, toDo.getDescription());
            preparedStatement.setString(4, toDo.getCreatedOn().toString());
            preparedStatement.setString(5, toDo.getDeadline().toString());
            preparedStatement.setLong(6, toDo.getParentToDoId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка сохранения задачи.");
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
            System.err.println("Ошибка изменения задачи.");
        }
        return toDo;
    }

    private List<ToDo> getToDos(long id, String sql) {
        List<ToDo> todos = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                todos.add(toDoMapper.toModel(resultSet));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка запроса списка задач");
        }
        return todos;
    }

    @Override
    public List<ToDo> showInnersToDo(long parenId) {
        return getToDos(parenId, FIND_ALL_BY_PARENT_TODO_ID);
    }

    @Override
    public List<ToDo> findAllNotFinishedAndNotNotifiedTasksByUserId(long userId) {
        return getToDos(userId, FIND_ALL_NOT_FINISHED_AND_NOT_NOTIFIED_BY_USER_ID);
    }

    @Override
    public void setToDoNotified(long id) {
        queryById(id, SET_NOTIFIED_TRUE);
    }

    @Override
    public List<ToDo> findAllNotFinishedTasksByUserId(Long userId) {
        return getToDos(userId, FIND_ALL_NOT_FINISHED_BY_USER_ID);
    }

    @Override
    public List<ToDo> findAllFinishedTasksByUserId(Long userId) {
        return getToDos(userId, FIND_ALL_FINISHED_BY_USER_ID);
    }

    @Override
    public ToDo findByIdAndUserId(Long id, Long userId) {
        ToDo todo = new ToDo();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_AND_USER_ID)) {
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
        queryById(id, SET_TODO_DONE);
    }

    private void queryById(long id, String sql) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Ошибка запроса");
        }
    }

    @Override
    public void deleteToDo(long id) {
        if (showInnersToDo(id).size() == 0) {
            queryById(id, DELETE_BY_ID);
        } else System.err.println("В данной задаче имеются вложенные задачи.");
    }
}
