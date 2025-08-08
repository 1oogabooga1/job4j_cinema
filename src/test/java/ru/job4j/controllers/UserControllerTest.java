package ru.job4j.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.models.User;
import ru.job4j.services.UserService;

import java.util.Optional;

class UserControllerTest {

    private  UserService userService;

    private  UserController userController;

    @BeforeEach
    public void init() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
    }

    @Test
    void whenRegisterIsSuccessful() {
        var user = new User(1, "Dmitrii", "email", "password");
        when(userService.save(user)).thenReturn(Optional.of(user));

        var model = new ConcurrentModel();
        var view = userController.register(user, model);

        assertThat(view).isEqualTo("redirect:/");
    }

    @Test
    void whenRegisterTwoUsersWithTheSameEmails() {
        var user1 = new User(1, "Dmitrii", "email", "password");
        var user2 = new User(2, "Dmitrii", "email", "password");
        when(userService.save(user1)).thenReturn(Optional.of(user1));
        when(userService.save(user2)).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var firstView = userController.register(user1, model);
        var secondView = userController.register(user2, model);

        assertThat(firstView).isEqualTo("redirect:/");
        assertThat(secondView).isEqualTo("errors/404");
        assertThat(model.getAttribute("error"))
                .isEqualTo("Пользователь с такой почтой уже существует");
    }

    @Test
    void whenLoginIsSuccessful() {
        var user = new User(1, "Dmitrii", "email", "password");
        when(userService.findByEmailAndPassword(any(), any())).thenReturn(Optional.of(user));

        var request = mock(HttpServletRequest.class);
        var session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);

        var model = new ConcurrentModel();
        var view = userController.login(user, model, request);

        assertThat(view).isEqualTo("redirect:/");
        verify(session).setAttribute("user", user);
    }

    @Test
    void whenLoginIsUnSuccessful() {
        var user = new User(1, "Dmitrii", "email", "password");
        when(userService.findByEmailAndPassword(any(), any())).thenReturn(Optional.empty());

        var request = mock(HttpServletRequest.class);
        var model = new ConcurrentModel();
        var view = userController.login(user, model, request);

        assertThat(view).isEqualTo("users/login");
        assertThat(model.getAttribute("error")).isEqualTo("Почта или пароль введены неправильно");
    }
}