package ru.jtasker.repository;

import org.springframework.stereotype.Repository;
import ru.jtasker.domain.User;
import ru.jtasker.mapper.UserMapper;

import java.sql.Connection;

@Repository
public class UsersRepositoryImpl implements UsersRepository {

    private final Connection connection;
    private final UserMapper userMapper;

    public UsersRepositoryImpl(Connection connection, UserMapper userMapper) {
        this.connection = connection;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        return null;
    }
}
