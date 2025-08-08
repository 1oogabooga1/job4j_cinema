package ru.job4j.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.dto.FIlmSessionDto;
import ru.job4j.services.FilmSessionService;

import java.time.LocalDateTime;
import java.util.List;

class FilmSessionControllerTest {

    private FilmSessionService filmSessionService;

    private FilmSessionController filmSessionController;

    @BeforeEach
    public void init() {
        filmSessionService = mock(FilmSessionService.class);
        filmSessionController = new FilmSessionController(filmSessionService);
    }

    @Test
    void whenGetAll() {
        var firstSession = new FIlmSessionDto(1, "Name", 1, 1, 1, LocalDateTime.now(),
                LocalDateTime.now(), 100);
        var secondSession = new FIlmSessionDto(2, "Name", 2, 2, 2, LocalDateTime.now(),
                LocalDateTime.now(), 200);
        List<FIlmSessionDto> sessions = List.of(firstSession, secondSession);
        when(filmSessionService.findAll()).thenReturn(sessions);

        var model = new ConcurrentModel();
        var view = filmSessionController.getAll(model);

        assertThat(model.getAttribute("filmSessions")).isEqualTo(sessions);
        assertThat(view).isEqualTo("filmSessions/list");
    }
}