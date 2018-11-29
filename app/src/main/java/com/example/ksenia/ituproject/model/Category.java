package com.example.ksenia.ituproject.model;

public class Category {

    private String title;

    private int colour;

    public Category(String title, int colour) {
        this.title = title;
        this.colour = colour;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getColour() {
        return colour;
    }

    public void setColour(int colour) {
        this.colour = colour;
    }
}
