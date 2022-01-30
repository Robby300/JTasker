package ru.jtasker.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.jtasker.domain.ToDo;
import ru.jtasker.domain.User;
import ru.jtasker.repository.ToDoesRepository;
import ru.jtasker.repository.UsersRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

@Component
public class UserInterface {

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
                enterUser(scanner);
                break;
            case "3":
                System.out.println("Список зарегистрированных пользователей:");
                usersRepository.findAll().forEach(System.out::println);
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

    private void enterUser(Scanner scanner){
        User currentUser;
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
}
