package ru.job4j.models;

import java.util.Map;
import java.util.Objects;

public class Film {

    public static final Map<String, String> MAP_COLUMN = Map.of(
            "id", "id",
            "name", "name",
            "description", "description",
            "year", "year",
            "genre_id", "genreId",
            "minimal_age", "minimalAge",
            "duration_in_minutes", "durationInMinutes",
            "file_id", "fileId"
    );

    private int id;

    private String name;

    private String description;

    private int year;

    private int genreId;

    private int minimalAge;

    private int durationInMinutes;

    private int fileId;

    public Film(int id, String name, String description, int year, int genreId,
                int minimalAge, int durationInMinutes, int fileId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.year = year;
        this.genreId = genreId;
        this.minimalAge = minimalAge;
        this.durationInMinutes = durationInMinutes;
        this.fileId = fileId;
    }

    public Film() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public int getMinimalAge() {
        return minimalAge;
    }

    public void setMinimalAge(int minimalAge) {
        this.minimalAge = minimalAge;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Film film)) {
            return false;
        }
        return getId() == film.getId() && getYear() == film.getYear() && getGenreId() == film.getGenreId() && getMinimalAge() == film.getMinimalAge() && getDurationInMinutes() == film.getDurationInMinutes() && getFileId() == film.getFileId() && Objects.equals(getName(), film.getName()) && Objects.equals(getDescription(), film.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getYear(), getGenreId(), getMinimalAge(), getDurationInMinutes(), getFileId());
    }
}
