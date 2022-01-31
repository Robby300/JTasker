package ru.jtasker.repository;

import ru.jtasker.domain.User;

import java.util.List;

public interface UsersRepository {
    User save(User user);

    List<User> findAll();

    boolean findUser(User user);
}
