package com.alexlis.tests.domain;

public enum MenuItem {

    ALL("Собака"),
    NEWS("Кошка"),
    VIDEOS("Утка");

    private String description;

    MenuItem(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}