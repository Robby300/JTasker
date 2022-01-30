package ru.jtasker.service;

import org.springframework.stereotype.Service;
import ru.jtasker.domain.ToDo;

import java.util.List;
import java.util.Scanner;

@Service
public interface ToDoService {
    void createAndSaveToDo(Scanner scanner, long id);
    void deleteToDo(long id);
    void toDoDone(long id);
    void editToDo(Scanner scanner, ToDo currentToDo);
    List<ToDo> findAllNotFinishedTasksByUserId(long userId);
    List<ToDo> findAllFinishedTasksByUserId(long userId);
    List<ToDo> showInnersToDo(long id);
    ToDo findByIdAndUserId(long todoId, long userId);
}
