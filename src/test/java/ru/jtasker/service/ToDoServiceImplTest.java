package ru.jtasker.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jtasker.domain.ToDo;
import ru.jtasker.repository.ToDosRepository;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToDoServiceImplTest {
    @Mock
    ToDosRepository toDosRepository;

    @InjectMocks
    ToDoServiceImpl toDoService;

    public ToDo returnToDo = new ToDo.Builder()
            .id(1L)
            .userId(2L)
            .name("testToDo")
            .description("toDosDescription")
            .createdOn(LocalDateTime.now())
            .deadline(LocalDateTime.now().plusHours(1).minusMinutes(1))
            .isDone(false)
            .isNotified(false)
            .parentToDoId(3L)
            .build();

    public ToDo findByIdAndUserId(Long id, Long userId) {}

        @Test
    void returnedToDoByIdAndUserIdIsCorrect() {
        when(toDosRepository.findByIdAndUserId(1L, 2L)).thenReturn(returnToDo);

        ToDo toDo = toDoService.findByIdAndUserId(1L, 2L);
        assertFalse(toDo == null);
        assertThat(toDo.getId(), equalTo(1L));
        assertThat(toDo.getName(), equalTo("testToDo"));
        assertThat(toDo.getDescription(), equalTo("toDosDescription"));
    }
}