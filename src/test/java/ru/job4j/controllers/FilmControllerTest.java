package ru.job4j.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.dto.FilmDto;
import ru.job4j.services.FilmService;

import java.util.List;

class FilmControllerTest {

    private FilmService filmService;

    private FilmController filmController;

    @BeforeEach
    public void init() {
        filmService = mock(FilmService.class);
        filmController = new FilmController(filmService);
    }

    @Test
    void whenGetAll() {
        var firstFilm = new FilmDto(1, "film", "desc", 2024, "detective", 18,
                120, 1);
        var secondFilm = new FilmDto(2, "film", "desc", 2024, "detective", 18,
                120, 2);
        var films = List.of(firstFilm, secondFilm);
        when(filmService.findAll()).thenReturn(films);

        var model = new ConcurrentModel();
        var view = filmController.getAll(model);

        assertThat(view).isEqualTo("films/list");
        assertThat(model.getAttribute("films")).isEqualTo(films);
    }

}