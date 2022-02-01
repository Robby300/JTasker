package ru.jtasker.service;

import org.springframework.stereotype.Service;
import ru.jtasker.domain.User;

import java.util.List;

@Service
public interface UserService {
    void loginUser(User user);

    void saveUser(User user);

    List<User> findAllUsers();

    User getCurrentUser();

    void setCurrentUserToNull();

    boolean currentUserIsLogin();
}
