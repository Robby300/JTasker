package ru.jtasker.service;

import org.springframework.stereotype.Service;
import ru.jtasker.domain.User;

import java.util.List;
import java.util.Scanner;

@Service
public interface UserService {
    void loginUser(Scanner scanner);

    void createAndSaveUser(Scanner scanner);

    List<User> findAllUsers();

    User getCurrentUser();

    void setCurrentUserToNull();
}
