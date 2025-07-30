package ru.job4j.repositories;

import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import ru.job4j.models.Hall;

import java.util.Optional;

@Repository
public class Sql2oHallRepository implements HallRepository {

    private Sql2o sql2o;

    public Sql2oHallRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Hall> findById(int id) {
        try (var connection = sql2o.open()) {
            var sql = """
                    SELECT * FROM halls WHERE id = :id
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("id", id);
            return Optional.ofNullable(query.setColumnMappings(Hall.MAP_COLUMN).executeAndFetchFirst(Hall.class));
        }
    }
}
