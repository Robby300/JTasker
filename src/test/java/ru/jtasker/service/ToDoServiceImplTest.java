package ru.jtasker.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jtasker.repository.ToDosRepository;

@ExtendWith(MockitoExtension.class)
class ToDoServiceImplTest {
    @Mock
    ToDosRepository repository;

    @InjectMocks
    ToDoServiceImpl toDoService;

    /*public ToDo returnToDo = new ToDo.Builder()
            .id(1L)
            .*/
}