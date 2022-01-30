package ru.jtasker.repository;

import ru.jtasker.domain.User;

import java.util.List;

public interface UsersRepository {
    User save(User user);

    List<User> findAll();

    User findCurrentUserByUserNameAndPassword(String userName, String password);

    User getCurrentUser();
    void setCurrentUserToNull();
}
