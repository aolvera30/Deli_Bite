package com.pluralsight.Models;

public class Chips extends MenuItem {
    private static final double BASE_PRICE = 1.50; // Adjusted price for chips

    public Chips(String name, double price) {
        super(name, BASE_PRICE);
    }

    @Override
    public String toString() {
        return String.format("Chips: %s, Price: $%.2f", getName(), getPrice());
    }
}