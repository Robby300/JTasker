package ru.jtasker;


import ru.jtasker.domain.ToDo;
import ru.jtasker.domain.User;

import java.time.LocalDateTime;

public class Application {

    public static void main(String[] args) {
        User user = new User.Builder().id(1L).userName("Vasya").email("Sobaka@mail.ru").password("123").build();
        ToDo toDo = new ToDo.Builder()
                .id(2L)
                .userId(1L)
                .name("выспасться")
                .description("Необходиммо выспаться")
                .createdOn(LocalDateTime.now())
                .deadline(LocalDateTime.MAX)
                .isDone(false)
                .parentToDoId(3).build();
        int i = 0;
    }

}
