package ru.job4j.services;

import ru.job4j.models.Ticket;

import java.util.Optional;

public interface TicketService {
    Optional<Ticket> save(Ticket ticket);

    Optional<Ticket> findById(int id);

}
