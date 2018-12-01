package com.example.ksenia.ituproject.model;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String title;

    private int colour;

    private List<Operation> operations = new ArrayList<>();

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

    public void addOperation(Operation o) {
        operations.add(o);
    }
}
