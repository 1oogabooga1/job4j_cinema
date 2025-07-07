package ru.job4j.models;

import java.util.Objects;

public class File {

    private int id;

    private String name;

    private String path;

    public File(int id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public File() {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof File file)) {
            return false;
        }
        return getId() == file.getId() && Objects.equals(getName(), file.getName()) && Objects.equals(getPath(), file.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPath());
    }
}
