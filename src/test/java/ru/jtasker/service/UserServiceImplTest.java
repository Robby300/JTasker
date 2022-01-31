package ru.jtasker.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.jtasker.domain.User;
import ru.jtasker.repository.UsersRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    UsersRepository usersRepository;

    @InjectMocks
    UserServiceImpl userService;

    public User returnUser = new User.Builder()
            .id(1L)
            .userName("Oleg")
            .password("123")
            .build();

    /*@Test
    void returnedUserByIdIsCorrect() {
        when(usersRepository.findUser()).thenReturn(returnUser);

        User user = userService.loginUser();
        assertFalse(user == null);
        assertThat(user.getId(), equalTo(1L));
        assertThat(user.getUsername(), equalTo("Oleg"));
        assertThat(user.getPassword(), equalTo("123"));
    }*/
}