package ru.jtasker.service;

import org.springframework.stereotype.Component;
import ru.jtasker.domain.ToDo;
import ru.jtasker.repository.ToDosRepository;
import ru.jtasker.repository.UsersRepository;

import java.sql.SQLException;
import java.util.List;

@Component
public class ToDoServiceImpl implements ToDoService {

    private final ToDosRepository toDosRepository;
    private final UsersRepository usersRepository;
    private final UserService userService;

    public ToDoServiceImpl(ToDosRepository toDosRepository, UsersRepository usersRepository, UserService userService) {
        this.toDosRepository = toDosRepository;
        this.usersRepository = usersRepository;
        this.userService = userService;
    }

    @Override
    public List<ToDo> showInnersToDo(long id) {
        return toDosRepository.showInnersToDo(id);
    }

    @Override
    public ToDo findByIdAndUserId(long todoId, long userId) {
        return toDosRepository.findByIdAndUserId(todoId, userId);
    }

    @Override
    public void saveToDo(ToDo toDo) throws SQLException {
        toDosRepository.save(toDo);
    }

    @Override
    public void deleteToDo(long id) {
        toDosRepository.deleteToDo(id);
    }

    @Override
    public void toDoDone(long id) {
        toDosRepository.toDoDone(id);
    }

    @Override
    public void setToDoNotified(long id) {
        toDosRepository.setToDoNotified(id);
    }

    @Override
    public void editToDo(ToDo toDo) {
        System.out.println(toDosRepository.editToDo(toDo));
    }

    @Override
    public List<ToDo> findAllNotFinishedTasksByUserId(long userId) {
        return toDosRepository.findAllNotFinishedTasksByUserId(userId);
    }

    @Override
    public List<ToDo> findAllNotFinishedAndNotNotifiedTasksByUserId(long userId) {
        return toDosRepository.findAllNotFinishedAndNotNotifiedTasksByUserId(userId);
    }

    @Override
    public List<ToDo> findAllFinishedTasksByUserId(long userId) {
        return toDosRepository.findAllFinishedTasksByUserId(userId);
    }

}
