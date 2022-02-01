package ru.jtasker.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.jtasker.domain.ToDo;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("ToDoService tests")
class ToDoServiceImplTest extends AbstractServiceTest {

    @Autowired
    ToDoService toDoService;

    @Test
    @DisplayName("findByIdAndUserId() should return correct todo if task and user exist")
    void returnedToDoByIdAndUserIdIsCorrect() {
        ToDo toDo = toDoService.findByIdAndUserId(1L, 1L);

        assertNotNull(toDo);
        assertThat(toDo.getId(), equalTo(1L));
        assertThat(toDo.getName(), equalTo("testTask"));
        assertThat(toDo.getDescription(), equalTo("newTestTask"));
    }

    @Test
    @DisplayName("findByIdAndUserId() should return null todo if task and user don't exist")
    void returnedNullToDoByIdAndUserIdIsCorrect() {
        ToDo toDo = toDoService.findByIdAndUserId(9999L, 1L);

        assertThat(toDo.getId(), equalTo(0L));
    }
}