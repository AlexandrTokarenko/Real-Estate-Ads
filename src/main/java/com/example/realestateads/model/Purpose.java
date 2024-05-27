package com.example.realestateads.model;

public enum Purpose {

    RENT("Надання в оренду"),
    SALE("Продаж");

    private final String title;

    Purpose(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
