package ru.job4j.repositories;

import ru.job4j.models.Genre;

import java.util.Optional;

public interface GenreRepository {

    Optional<Genre> findById(int id);

}
