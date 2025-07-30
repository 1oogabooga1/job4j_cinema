package ru.job4j.services;

import ru.job4j.models.Hall;

import java.util.Optional;

public interface HallService {

    Optional<Hall> findById(int id);
}
