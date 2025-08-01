package ru.job4j.repositories;

import ru.job4j.models.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> save(User user);

    Optional<User> findByEmailAndPassword(String email, String password);

}
