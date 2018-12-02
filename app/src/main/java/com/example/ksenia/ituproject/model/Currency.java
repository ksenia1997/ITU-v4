package com.example.ksenia.ituproject.model;

public class Currency {

    private String name;

    private float rate;  // kurz, for main currency == 1

    // true -> 1 unit of main currency == `rate` units of this currency
    // false -> `rate` units of main currency == 1 unit of this currency
    private boolean direction;

    public Currency(String name, float rate, boolean direction) {
        this.name = name;
        this.rate = rate;
        this.direction = direction;
    }

    public Currency(String name, double rate, boolean direction) {
        this(name, (float) rate, direction);
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

    private float getRateInFalseDirection() {
        return direction ? 1 / rate : rate;
    }

    void mainCurrencyChanged(Currency mainCurrency) {
        // for example:
        // last main currency = CZK
        // this = USD
        // main currency = EUR
        if (this.getRateInFalseDirection() < mainCurrency.getRateInFalseDirection()) {
            rate = mainCurrency.getRateInFalseDirection() / this.getRateInFalseDirection();
            direction = true;
        } else {
            rate = this.getRateInFalseDirection() / mainCurrency.getRateInFalseDirection();
            direction = false;
        }
    }

    void setAsMainCurrency() {
        rate = 1;
    }
}
