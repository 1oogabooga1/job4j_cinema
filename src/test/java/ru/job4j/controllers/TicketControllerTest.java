package ru.job4j.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.models.Ticket;
import ru.job4j.models.User;
import ru.job4j.services.FilmService;
import ru.job4j.services.FilmSessionService;
import ru.job4j.services.HallService;
import ru.job4j.services.TicketService;

import java.util.Optional;

class TicketControllerTest {

    private FilmSessionService filmSessionService;

    private TicketService ticketService;

    private FilmService filmService;

    private HallService hallService;

    private TicketController ticketController;

    @BeforeEach
    public void init() {
        filmSessionService = mock(FilmSessionService.class);
        ticketService = mock(TicketService.class);
        filmService = mock(FilmService.class);
        hallService = mock(HallService.class);
        ticketController = new TicketController(filmSessionService, ticketService, filmService, hallService);
    }

    @Test
    void whenSuccessfullyBuy() {
        var user = new User(1, "dmitrii", "email", "password");
        var session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(user);

        var ticket = new Ticket(1, 1, 1, 1, 0);
        when(ticketService.save(ticket)).thenReturn(Optional.of(ticket));

        var model = new ConcurrentModel();
        var view = ticketController.buy(ticket, model, session);

        assertThat(view).isEqualTo("tickets/successful");
        assertThat(ticket.getUserId()).isEqualTo(user.getId());
    }

    @Test
    void whenUnSuccessfullyBuy() {
        var user = new User(1, "dmitrii", "email", "password");
        var session = mock(HttpSession.class);
        when(session.getAttribute("user")).thenReturn(user);

        var ticket = new Ticket(1, 1, 1, 1, 0);
        when(ticketService.save(ticket)).thenReturn(Optional.empty());

        var model = new ConcurrentModel();
        var view = ticketController.buy(ticket, model, session);

        assertThat(view).isEqualTo("errors/404");
        assertThat(model.getAttribute("error")).isEqualTo("Не удалось приобрести билет "
                + "на заданное место. Вероятно оно уже занято. Перейдите на страницу бронирования билетов и попробуйте снова.");
        assertThat(ticket.getUserId()).isEqualTo(user.getId());
    }
}