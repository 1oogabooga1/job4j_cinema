package ru.job4j.repositories;

import ru.job4j.models.Ticket;

import java.util.Optional;

public interface TicketRepository {
    Optional<Ticket> save(Ticket ticket);

    Optional<Ticket> findById(int id);

}
