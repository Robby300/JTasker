package ru.jtasker.ui;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.User;
import ru.jtasker.service.UserService;

import java.util.Scanner;

@Component
public class UserInterface {
    private final UserService userService;

    public UserInterface(UserService userService) {
        this.userService = userService;
    }

    public void printUserInterface() {
        System.out.println("""
                1. Войти
                2. Зарегистрироваться
                3. Список пользователей
                4. Завершить приложение.""");
    }

    public void insertCommand(Scanner scanner) {
        String command = scanner.nextLine();
        switch (command) {
            case "1" -> userService.loginUser(createUser(scanner));
            case "2" -> userService.saveUser(createUser(scanner));
            case "3" -> userService.findAllUsers().forEach(System.out::println);
            case "4" -> {
                System.out.println("GoodBye...");
                System.exit(0);
            }
            default -> System.err.println("Введите число от 1 до 4х");
        }
    }

    private User createUser(Scanner scanner) {

        System.out.println("Введите имя пользователя");
        String userName = scanner.nextLine();
        System.out.println("Введите пароль");
        String password = scanner.nextLine();

        User user = new User.Builder()
                .userName(userName)
                .password(password)
                .build();
        return user;
    }
}
