package ru.job4j.repositories;

import ru.job4j.models.FilmSession;

import java.util.Collection;
import java.util.Optional;

public interface FilmSessionRepository {
    Optional<FilmSession> findById(int id);

    Collection<FilmSession> findAll();
}
