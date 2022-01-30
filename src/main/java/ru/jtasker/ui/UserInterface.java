package ru.jtasker.ui;

import org.springframework.stereotype.Component;
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
            case "1" -> userService.loginUser(scanner);
            case "2" -> userService.createAndSaveUser(scanner);
            case "3" -> userService.findAllUsers().forEach(System.out::println);
            case "4" -> {
                System.out.println("GoodBye...");
                System.exit(0);
            }
            default -> System.err.println("Введите число от 1 до 4х");
        }
    }
}
