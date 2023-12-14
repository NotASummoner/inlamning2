package org.example;

public class Entity {

    private int id;
    protected String name;

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getDescription() {
        return "ID: " + id + ", Name: " + name;
    }

    public String getCSV() {
        return id + "," + name;
    }

    public int getId() {
        return id;
    }
}
