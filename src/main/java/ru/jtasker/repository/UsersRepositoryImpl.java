package ru.jtasker.repository;

import ru.jtasker.domain.User;

import java.sql.Connection;

public class UsersRepositoryImpl implements UsersRepository {

    private final Connection connection;

    public UsersRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User save(User user) {
        return null;
    }
}
