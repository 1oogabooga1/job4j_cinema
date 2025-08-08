package ru.job4j.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.job4j.configurations.DataSourceConfiguration;
import ru.job4j.models.User;

import java.util.Properties;

class Sql2oUserRepositoryTest {

    private static Sql2oUserRepository repository;

    @BeforeAll
    public static void init() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oUserRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DataSourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);
        var sql2o = configuration.databaseClient(datasource);

        repository = new Sql2oUserRepository(sql2o);
    }

    @AfterEach
    public void deleteUsers() {
        repository.deleteAllUsers();
    }

    @Test
    void whenSaveThenGet() {
        User user = repository.save(new User(1, "Dmitrii", "Email", "password")).get();

        assertThat(user).usingRecursiveComparison().isEqualTo(repository.findByEmailAndPassword(user.getEmail(), user.getPassword()).get());
    }

    @Test
    void whenFindByWrongEmailOrPasswordThenEmpty() {
        repository.save(new User(0, "Dmitrii", "user@example.com", "pass123"));

        var result1 = repository.findByEmailAndPassword("wrong@example.com", "pass123");
        var result2 = repository.findByEmailAndPassword("user@example.com", "wrongpass");

        assertThat(result1).isEmpty();
        assertThat(result2).isEmpty();
    }
}