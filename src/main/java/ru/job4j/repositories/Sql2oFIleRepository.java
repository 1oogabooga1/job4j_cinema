package ru.job4j.repositories;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.models.File;

import java.util.Optional;

@Repository
public class Sql2oFIleRepository implements FileRepository {

    private Sql2o sql2o;

    public Sql2oFIleRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<File> findById(int id) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM files WHERE id = :id")
                    .addParameter("id", id);
            var file = query.executeAndFetchFirst(File.class);
            return Optional.ofNullable(file);
        }
    }
}
