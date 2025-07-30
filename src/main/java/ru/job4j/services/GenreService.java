package ru.job4j.services;

import ru.job4j.models.Genre;

import java.util.Optional;

public interface GenreService {
    Optional<Genre> findById(int id);
}
