package ru.jtasker.service;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.User;
import ru.jtasker.repository.UsersRepository;

import java.util.List;
import java.util.Scanner;

@Component
public class UserServiceImpl implements UserService {
    private User currentUser;

    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void loginUser(Scanner scanner) {
        System.out.println("Произведите вход:");
        System.out.println("Введите имя пользователя");
        String userName = scanner.nextLine();
        System.out.println("Введите пароль");
        String password = scanner.nextLine();
        currentUser = usersRepository.findCurrentUserByUserNameAndPassword(userName, password);
        if (currentUserIsLogin()) System.out.println("Здравствуйте " + currentUser.getUserName());
    }

    @Override
    public void createAndSaveUser(Scanner scanner) {
        System.out.println("Вы выбрали регистрацию пользователя:");
        System.out.println("Введите имя пользователя");
        String userName = scanner.nextLine();
        System.out.println("Введите пароль");
        String password = scanner.nextLine();
        System.out.println("Введите почту");
        String email = scanner.nextLine();
        User user = new User.Builder()
                .userName(userName)
                .password(password)
                .email(email)
                .build();
        System.out.println(usersRepository.save(user));
        System.out.println("Регистрация успешно завершена.");
    }

    @Override
    public List<User> findAllUsers() {
        System.out.println("Список зарегистрированных пользователей:");
        return usersRepository.findAll();
    }

    @Override
    public User getCurrentUser() {
        return usersRepository.getCurrentUser();
    }

    @Override
    public void setCurrentUserToNull() {
        usersRepository.setCurrentUserToNull();
    }

    public boolean currentUserIsLogin() {
        return currentUser != null && currentUser.getUserName() != null;
    }
}
