package ru.job4j.repositories;

import ru.job4j.models.Film;

import java.util.Collection;
import java.util.Optional;

public interface FilmRepository {

    Optional<Film> findById(int id);

    Collection<Film> findAll();
}
