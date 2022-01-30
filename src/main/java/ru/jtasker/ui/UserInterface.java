package ru.jtasker.ui;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.User;
import ru.jtasker.repository.ToDoesRepository;
import ru.jtasker.repository.UsersRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@Component
public class UserInterface {

    private User currentUser;
    private final ToDoesRepository toDoesRepository;
    private final UsersRepository usersRepository;

    public UserInterface(ToDoesRepository toDoesRepository, UsersRepository usersRepository) {
        this.toDoesRepository = toDoesRepository;
        this.usersRepository = usersRepository;
    }

    public void printUserInterface() {
        System.out.println("1. Войти\n2. Зарегистрироваться\n3. Список пользователей");
    }

    public void insertCommand(Scanner scanner) throws SQLException {
        String command = scanner.nextLine();

        switch (command) {
            case "1":
                createAndSaveUser(scanner);
                break;
            case "2":
                loginUser(scanner);
                break;
            case "3":
                findAllUsers().forEach(System.out::println);
                break;
            default:
                System.out.println("Введите число от 1 до 2х");
                break;
        }
    }

    private void createAndSaveUser(Scanner scanner) throws SQLException {
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

        usersRepository.save(user);
        System.out.println("Регистрация успешно завершена.");
    }

    private void loginUser(Scanner scanner){
        System.out.println("Произведите вход:");

        System.out.println("Введите имя пользователя");
        String userName = scanner.nextLine();

        System.out.println("Введите пароль");
        String password = scanner.nextLine();

        currentUser = usersRepository.findUserbyUserNameAndPassword(userName, password);
        if (currentUser == null) {
            System.out.println("Неверное имя пользователя или пароль.");
        } else System.out.println("Здравствуйте " + currentUser.getUserName());
    }
    private List<User> findAllUsers() {
        System.out.println("Список зарегистрированных пользователей:");
        return usersRepository.findAll();
    }

    private boolean currentUserIsLogin() {
        return currentUser != null;
    }
}
