package ru.job4j.repositories;

import ru.job4j.models.Hall;

import java.util.Optional;

public interface HallRepository {

    Optional<Hall> findById(int id);

}
