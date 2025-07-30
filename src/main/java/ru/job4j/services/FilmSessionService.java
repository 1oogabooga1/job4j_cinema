package ru.job4j.services;

import ru.job4j.dto.FIlmSessionDto;

import java.util.Collection;
import java.util.Optional;

public interface FilmSessionService {
    Optional<FIlmSessionDto> findById(int id);

    Collection<FIlmSessionDto> findAll();
}
