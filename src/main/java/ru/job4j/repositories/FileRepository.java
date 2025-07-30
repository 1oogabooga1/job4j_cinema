package ru.job4j.repositories;

import ru.job4j.models.File;

import java.util.Optional;

public interface FileRepository {

    Optional<File> findById(int id);

}
