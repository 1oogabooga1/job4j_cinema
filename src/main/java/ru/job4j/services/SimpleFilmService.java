package ru.job4j.services;

import org.springframework.stereotype.Service;
import ru.job4j.dto.FilmDto;
import ru.job4j.models.Film;
import ru.job4j.models.Genre;
import ru.job4j.repositories.FilmRepository;
import ru.job4j.repositories.GenreRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SimpleFilmService implements FilmService {

    private final FilmRepository filmRepository;

    private final GenreRepository genreRepository;

    public SimpleFilmService(FilmRepository sql2oFilmRepository, GenreRepository sq2loGenreRepository) {
        this.filmRepository = sql2oFilmRepository;
        this.genreRepository = sq2loGenreRepository;
    }

    @Override
    public Collection<FilmDto> findAll() {
        List<Film> films = (List<Film>) filmRepository.findAll();
        Collection<FilmDto> result = new ArrayList<>();
        for (Film film : films) {
            result.add(new FilmDto(film.getId(), film.getName(), film.getDescription(), film.getYear(),
                    genreRepository.findById(film.getGenreId()).get().getName(), film.getMinimalAge(),
                    film.getDurationInMinutes(), film.getFileId()));
        }
        return result;
    }

    @Override
    public Optional<FilmDto> findById(int id) {
        Film film = filmRepository.findById(id).get();
        Genre genre = genreRepository.findById(film.getGenreId()).get();
        return Optional.of(new FilmDto(film.getId(), film.getName(), film.getDescription(), film.getYear(),
                genre.getName(), film.getMinimalAge(), film.getDurationInMinutes(), film.getFileId()));
    }
}
