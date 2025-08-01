package ru.job4j.services;


import org.springframework.stereotype.Service;
import ru.job4j.models.Hall;
import ru.job4j.repositories.HallRepository;

import java.util.Optional;

@Service
public class SimpleHallService implements HallService {

    private final HallRepository hallRepository;

    public SimpleHallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public Optional<Hall> findById(int id) {
        return hallRepository.findById(id);
    }
}
