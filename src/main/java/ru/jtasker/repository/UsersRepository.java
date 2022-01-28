package ru.jtasker.repository;

import ru.jtasker.domain.User;

import java.util.List;

public interface UsersRepository {
    void save(User user);
}
