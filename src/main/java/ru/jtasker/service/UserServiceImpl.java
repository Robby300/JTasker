package ru.jtasker.service;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.User;
import ru.jtasker.repository.UsersRepository;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    private static User currentUser;

    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void loginUser(User user) {
        if (usersRepository.findUser(user.getUserName()).getPassword().equals(user.getPassword())) {
            currentUser = usersRepository.findUser(user.getUserName());
            System.out.println("Здравствуйте " + currentUser.getUserName());
        } else System.err.println("Неверные логин или пароль.");
    }

    @Override
    public void saveUser(User user) {
        usersRepository.save(user);
    }


    @Override
    public List<User> findAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUserToNull() {
        currentUser = null;
    }

    public boolean currentUserIsLogin() {
        return currentUser != null && currentUser.getUserName() != null;
    }
}
