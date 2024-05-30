package com.pluralsight.Models;

public class Chips extends MenuItem {
    private static final double BASE_PRICE = 1.50; // Adjusted price for chips

    public Chips(String item, double price) {
        super(item, BASE_PRICE);
    }

    @Override
    public String toString() {
        return String.format("Chips\n  Selection: %s\n  Price: $%.2f", getItem(), getPrice());
    }
}