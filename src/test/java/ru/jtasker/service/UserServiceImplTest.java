package ru.jtasker.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.jtasker.domain.User;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("UserService tests")
class UserServiceImplTest extends AbstractServiceTest {

    @Autowired
    UserService userService;

    @Test
    @DisplayName("loginUser() should login user successfully")
    void shouldLoginUserSuccessfully() {
        User user = new User();
        user.setUserName("Ivan");
        user.setPassword("123");

        userService.loginUser(user);
        user = userService.getCurrentUser();

        assertNotNull(user);
        assertThat(user.getId(), is(1L));
        assertThat(user.getUserName(), is("Ivan"));
        assertThat(user.getPassword(), is("123"));
    }

    @Test
    @DisplayName("findAllUsers() should return a List with two users")
    void shouldReturnListWithTwoUsers() {
        User user = new User();
        user.setUserName("Semen");
        user.setPassword("321");

        userService.saveUser(user);
        List<User> allUsers = userService.findAllUsers();

        assertNotNull(allUsers);
        assertFalse(allUsers.isEmpty());
        assertThat(allUsers.size(), is(2));
        assertThat(allUsers.get(1).getId(), is(2L));
        assertThat(allUsers.get(1).getUserName(), is("Semen"));
        assertThat(allUsers.get(1).getPassword(), is("321"));
    }
}