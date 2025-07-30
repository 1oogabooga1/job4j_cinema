package ru.job4j.services;

import org.springframework.stereotype.Service;
import ru.job4j.dto.FIlmSessionDto;
import ru.job4j.models.Film;
import ru.job4j.models.FilmSession;
import ru.job4j.repositories.FilmRepository;
import ru.job4j.repositories.FilmSessionRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleFilmSessionService implements FilmSessionService {

    private final FilmSessionRepository filmSessionRepository;

    private final FilmRepository filmRepository;

    public SimpleFilmSessionService(FilmSessionRepository sql2oFilmSessionRepository, FilmRepository sql2oFilmRepository) {
        this.filmSessionRepository = sql2oFilmSessionRepository;
        this.filmRepository = sql2oFilmRepository;
    }

    @Override
    public Collection<FIlmSessionDto> findAll() {
        Collection<FilmSession> sessions = filmSessionRepository.findAll();
        Collection<FIlmSessionDto> result = new ArrayList<>();
        for (FilmSession session : sessions) {
            Film film = filmRepository.findById(session.getFilmId()).get();
            result.add(new FIlmSessionDto(session.getId(), film.getName(), film.getId(), film.getFileId(),
                    session.getHallId(), session.getStartTime(), session.getEndTime(), session.getPrice()));
        }
        return result;
    }

    @Override
    public Optional<FIlmSessionDto> findById(int id) {
        FilmSession session = filmSessionRepository.findById(id).get();
        Film film = filmRepository.findById(session.getFilmId()).get();
        return Optional.of(new FIlmSessionDto(session.getId(), film.getName(), film.getId(), film.getFileId(),
                session.getHallId(), session.getStartTime(), session.getEndTime(), session.getPrice()));
    }
}
