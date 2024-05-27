package com.pluralsight.Models;

public class Chips extends MenuItem {
    // Constants for base prices
    private static final double BASE_PRICE = 1.00;

    public Chips(String name, double v) {
        super(name, BASE_PRICE); // Initial price is set to the base price
    }

    @Override
    public String toString() {
        return String.format("Chips: %s, Price: $%.2f",
                getName(), getPrice());
    }
}
