package ru.jtasker;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.jtasker.config.AppConfig;
import ru.jtasker.notifier.Notifier;
import ru.jtasker.service.UserService;
import ru.jtasker.ui.ToDoInterface;
import ru.jtasker.ui.UserInterface;

import java.sql.SQLException;
import java.util.Scanner;

//@Component
public class Application {

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserInterface ui = context.getBean(UserInterface.class);
        ToDoInterface ti = context.getBean(ToDoInterface.class);
        Notifier notifier = context.getBean(Notifier.class);
        UserService userService = context.getBean(UserService.class);

        System.out.println("JTasker app has started.");
        notifier.start();
        while (true) {
            if (userService.getCurrentUser() != null) {
                ti.printToDoListInterface();
                ti.insertCommandForRegisteredUser(scanner);
                Thread.yield();
            } else {
                ui.printUserInterface();
                ui.insertCommand(scanner);
            }
        }
    }
}



