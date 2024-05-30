package com.pluralsight.Models;

public class Drink extends MenuItem {
    private String size;

    // Constants for base prices based on size
    private static final double BASE_PRICE_SMALL = 2.00;
    private static final double BASE_PRICE_MEDIUM = 2.50;
    private static final double BASE_PRICE_LARGE = 3.00;

    public Drink(String item, String size, double v) {
        super(item, 0); // Initial price is set to 0, it will be calculated dynamically
        this.size = size;
    }

    // Getter and setter for size
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    // Calculate price based on size
    @Override
    public double getPrice() {
        switch (size.toLowerCase()) {
            case "small":
                return BASE_PRICE_SMALL;
            case "medium":
                return BASE_PRICE_MEDIUM;
            case "large":
                return BASE_PRICE_LARGE;
            default:
                return BASE_PRICE_MEDIUM; // Default to medium if size is unrecognized
        }
    }

    @Override
    public String toString() {
        return String.format("Drink: %s, Size: %s, Price: $%.2f",
                getItem(), size, getPrice());
    }
}