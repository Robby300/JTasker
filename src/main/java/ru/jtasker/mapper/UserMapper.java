package ru.jtasker.mapper;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserMapper {

    public User toModel(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getInt("id"));
            user.setUserName(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
        } catch (SQLException e) {
            System.err.println("Неверное имя пользователя или пароль");
            return null;
        }
        return user;
    }
}
