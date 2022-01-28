package ru.jtasker.repository;

import ru.jtasker.domain.User;

public interface UsersRepository {
    User save(User user);
}
