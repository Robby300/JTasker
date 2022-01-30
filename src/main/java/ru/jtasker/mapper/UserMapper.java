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
            user.setId(resultSet.getLong("id"));
            user.setUserName(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
