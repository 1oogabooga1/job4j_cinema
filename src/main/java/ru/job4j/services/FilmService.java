package ru.job4j.services;

import ru.job4j.dto.FilmDto;

import java.util.Collection;
import java.util.Optional;

public interface FilmService {
    Collection<FilmDto> findAll();

    Optional<FilmDto> findById(int id);
}
