package ru.job4j.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.configurations.DataSourceConfiguration;
import ru.job4j.models.Ticket;

import java.util.Properties;

class Sql2oTicketRepositoryTest {

    private static Sql2oTicketRepository ticketRepository;

    @BeforeAll
    public static void init() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oTicketRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DataSourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        ticketRepository = new Sql2oTicketRepository(sql2o);
    }

    @AfterEach
    public void deleteTickets() {
        ticketRepository.deleteAllTickets();
    }

    @Test
    void whenSaveThenFindByIdIsSuccessful() {
        var ticket = ticketRepository.save(new Ticket(1, 1, 1, 1, 1));

        assertThat(ticket).usingRecursiveComparison().isEqualTo(ticketRepository.findById(ticket.get().getId()));
    }

    @Test
    void whenNotSaveThenGetNothing() {
        assertThat(ticketRepository.findById(1)).isEmpty();
    }
}