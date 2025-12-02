package ru.job4j.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.dto.FIlmSessionDto;
import ru.job4j.dto.FilmDto;
import ru.job4j.models.Hall;
import ru.job4j.models.Ticket;
import ru.job4j.models.User;
import ru.job4j.services.FilmService;
import ru.job4j.services.FilmSessionService;
import ru.job4j.services.HallService;
import ru.job4j.services.TicketService;

@Controller
@RequestMapping("/tickets/")
public class TicketController {

    private final FilmSessionService filmSessionService;

    private final TicketService ticketService;

    private final FilmService filmService;

    private final HallService hallService;

    public TicketController(FilmSessionService filmSessionService, TicketService ticketService, FilmService filmService,
                            HallService hallService) {
        this.filmSessionService = filmSessionService;
        this.ticketService = ticketService;
        this.filmService = filmService;
        this.hallService = hallService;
    }

    @GetMapping("/buy/{id}")
    public String buy(@PathVariable int id, Model model) {
        FIlmSessionDto fIlmSessionDto = filmSessionService.findById(id).orElseThrow(() ->
                new RuntimeException("FILM SESSION EXCEPTION"));
        FilmDto filmDto = filmService.findById(fIlmSessionDto.getFilmId()).orElseThrow(() ->
                new RuntimeException("FILMDTO EXCEPTION"));
        Hall hall = hallService.findById(fIlmSessionDto.getHallId()).orElseThrow(() ->
                new RuntimeException("HALL EXCEPTION"));
        model.addAttribute("filmSession", fIlmSessionDto);
        model.addAttribute("filmDto", filmDto);
        model.addAttribute("hall", hall);
        return "tickets/buy";
    }

    @PostMapping("/buy")
    public String buy(@ModelAttribute Ticket ticket, Model model, HttpSession session) {
        var user = (User) session.getAttribute("user");
        ticket.setUserId(user.getId());
        var savedTicket = ticketService.save(ticket);
        if (savedTicket.isEmpty()) {
            model.addAttribute("error", "This place probably is already taken, please choose another one.");
            return "errors/404";
        }
        return "tickets/successful";
    }
}
