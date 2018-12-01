package com.example.ksenia.ituproject.model;

public class Currency {

    private String name;

    private float rate;  // kurz

    // true -> 1 unit of main currency == `rate` units of this currency
    // false -> `rate` units of main currency == 1 unit of this currency
    private boolean direction;

    public Currency(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return name;
    }

    public float getRate() {
        return rate;
    }
    public void setRate(float rate) {
        this.rate = rate;
    }

    public boolean getDirection() {
        return direction;
    }
    public void setDirection(boolean direction) {
        this.direction = direction;
    }
}
