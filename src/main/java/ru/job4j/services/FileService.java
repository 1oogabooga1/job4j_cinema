package ru.job4j.services;

import ru.job4j.dto.FileDto;

import java.util.Optional;

public interface FileService {
    Optional<FileDto> findById(int id);
}
