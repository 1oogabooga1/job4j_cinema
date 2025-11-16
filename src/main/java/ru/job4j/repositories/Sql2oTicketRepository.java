package ru.job4j.repositories;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import ru.job4j.models.Ticket;

import java.util.Optional;

@Slf4j
@Repository
public class Sql2oTicketRepository implements TicketRepository {

    private final Sql2o sql2o;

    public Sql2oTicketRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Optional<Ticket> save(Ticket ticket) {
        try (var connection = sql2o.open()) {
            var sql = """
                    INSERT INTO tickets(place_number, row_number, session_id, user_id) 
                    VALUES (:placeNumber, :rowNumber, :sessionId, :userId)
                    """;
            var query = connection.createQuery(sql, true)
                    .addParameter("placeNumber", ticket.getPlaceNumber())
                    .addParameter("rowNumber", ticket.getRowNumber())
                    .addParameter("sessionId", ticket.getSessionId())
                    .addParameter("userId", ticket.getUserId())
                    .executeUpdate();
            ticket.setId(query.getKey(Integer.class));
            return Optional.of(ticket);
        } catch (Sql2oException e) {
            if (e.getCause() instanceof PSQLException pg && "23505".equals(pg.getSQLState())) {
                    log.warn("Duplicate ticket detected: row={}, place={}, session={}, user={}",
                            ticket.getRowNumber(), ticket.getPlaceNumber(), ticket.getSessionId(), ticket.getUserId());
                    return Optional.empty();
            }
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public Optional<Ticket> findById(int id) {
        try (var connection = sql2o.open()) {
            var sql = """
                    SELECT * FROM tickets WHERE id = :id
                    """;
            var query = connection.createQuery(sql)
                    .addParameter("id", id);
            var ticket = query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetchFirst(Ticket.class);
            return Optional.ofNullable(ticket);
        }
    }

    public void deleteAllTickets() {
        try (var connection = sql2o.open()) {
            var sql = """
                    DELETE FROM tickets 
                    """;
            connection.createQuery(sql).executeUpdate();
        }
    }
}
