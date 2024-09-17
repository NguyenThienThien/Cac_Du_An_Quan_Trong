package com.longthph30891.note_mvp.model;

public class Note {
    private int id;
    private String name, description;

    public Note() {
    }

    public Note(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Note(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public Note setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Note setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Note setDescription(String description) {
        this.description = description;
        return this;
    }
}
