package ru.jtasker.repository;

import ru.jtasker.domain.User;

public interface UsersRepository {
    void save(User user);
}
