package ru.job4j.repositories;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.models.Genre;

import java.util.Optional;

@Repository
public class Sql2oGenreRepository implements GenreRepository {

    private Sql2o sql2o;

    public Sql2oGenreRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Genre> findById(int id) {
        try (var connection = sql2o.open()) {
            var sql = """
                    SELECT * FROM genres WHERE id = :id
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("id", id);
            return Optional.ofNullable(query.executeAndFetchFirst(Genre.class));
        }
    }
}
