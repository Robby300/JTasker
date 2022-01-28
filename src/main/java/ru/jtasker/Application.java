package ru.jtasker;


import ru.jtasker.domain.User;

public class Application {

    public static void main(String[] args) {
        User user = new User.Builder().id(1L).userName("Vasya").email("Sobaka@mail.ru").password("123").build();
        int i = 0;
    }

}
