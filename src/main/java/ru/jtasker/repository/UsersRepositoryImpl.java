package ru.jtasker.repository;

import org.springframework.stereotype.Repository;
import ru.jtasker.domain.ToDo;
import ru.jtasker.domain.User;
import ru.jtasker.mapper.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersRepositoryImpl implements UsersRepository {
    private static User currentUser = new User();

    private final Connection connection;
    private final UserMapper userMapper;

    private static final String INSERT_USER = "INSERT INTO users(" +
            "username, password, email) VALUES (?, ?, ?)";
    private static final String FIND_ALL = "SELECT * FROM users ";
    private static final String FIND_USER_BY_NAME_AND_PASSWORD
            = "SELECT * FROM users WHERE username = ? AND password = ?";


    public UsersRepositoryImpl(Connection connection, UserMapper userMapper) {
        this.connection = connection;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());;
            preparedStatement.setString(3, user.getEmail());

            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(userMapper.toModel(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findCurrentUserByUserNameAndPassword(String userName, String password) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_NAME_AND_PASSWORD)) {
            preparedStatement.setString(1, currentUser.getUserName());
            preparedStatement.setString(2, currentUser.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                currentUser = userMapper.toModel(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentUser;
    }


    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UsersRepositoryImpl.currentUser = currentUser;
    }

}
