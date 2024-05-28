package com.pluralsight.Models;

import java.util.List;

public class Sandwich extends MenuItem {
    private String breadType;
    private String size;
    private boolean toasted;
    private List<String> toppings;

    // Constants for base prices and topping prices
    public static final double BASE_PRICE_4 = 5.50;
    public static final double BASE_PRICE_8 = 7.00;
    public static final double BASE_PRICE_12 = 8.50;

    public static final double MEAT_PRICE_4 = 1.00;
    public static final double MEAT_PRICE_8 = 2.00;
    public static final double MEAT_PRICE_12 = 3.00;

    public static final double CHEESE_PRICE_4 = 0.75;
    public static final double CHEESE_PRICE_8 = 1.50;
    public static final double CHEESE_PRICE_12 = 2.25;

    public static final double EXTRA_MEAT_PRICE_4 = 0.50;
    public static final double EXTRA_MEAT_PRICE_8 = 1.00;
    public static final double EXTRA_MEAT_PRICE_12 = 1.50;

    public static final double EXTRA_CHEESE_PRICE_4 = 0.30;
    public static final double EXTRA_CHEESE_PRICE_8 = 0.60;
    public static final double EXTRA_CHEESE_PRICE_12 = 0.90;

    public Sandwich(String name, String breadType, String size, boolean toasted, List<String> toppings) {
        super(name, 0); // Initial price is set to 0, it will be calculated dynamically
        this.breadType = breadType;
        this.size = size;
        this.toasted = toasted;
        this.toppings = toppings;
    }

    public String getBreadType() {
        return breadType;
    }

    public String getSize() {
        return size;
    }

    public boolean isToasted() {
        return toasted;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public double getBasePrice() {
        switch (size) {
            case "4\"":
                return BASE_PRICE_4;
            case "8\"":
                return BASE_PRICE_8;
            case "12\"":
                return BASE_PRICE_12;
            default:
                return 0;
        }
    }

    public double getToppingPrice(List<String> toppings, double price4, double price8, double price12) {
        double total = 0;
        for (String topping : toppings) {
            switch (size) {
                case "4\"":
                    total += price4;
                    break;
                case "8\"":
                    total += price8;
                    break;
                case "12\"":
                    total += price12;
                    break;
            }
        }
        return total;
    }

    @Override
    public double getPrice() {
        double basePrice = getBasePrice();

        // Calculate prices for regular toppings
        double meatPrice = 0;
        double cheesePrice = 0;
        double extraMeatPrice = 0;
        double extraCheesePrice = 0;

        for (String topping : toppings) {
            switch (topping.toLowerCase()) {
                case "steak":
                case "ham":
                case "salami":
                case "roast beef":
                case "chicken":
                case "bacon":
                    meatPrice += getToppingPrice(List.of(topping), MEAT_PRICE_4, MEAT_PRICE_8, MEAT_PRICE_12);
                    break;
                case "american":
                case "provolone":
                case "cheddar":
                case "swiss":
                    cheesePrice += getToppingPrice(List.of(topping), CHEESE_PRICE_4, CHEESE_PRICE_8, CHEESE_PRICE_12);
                    break;
                case "extra meat":
                    extraMeatPrice += getToppingPrice(List.of(topping), EXTRA_MEAT_PRICE_4, EXTRA_MEAT_PRICE_8, EXTRA_MEAT_PRICE_12);
                    break;
                case "extra cheese":
                    extraCheesePrice += getToppingPrice(List.of(topping), EXTRA_CHEESE_PRICE_4, EXTRA_CHEESE_PRICE_8, EXTRA_CHEESE_PRICE_12);
                    break;
                // Add other cases for additional toppings if needed
            }
        }

        return basePrice + meatPrice + cheesePrice + extraMeatPrice + extraCheesePrice;
    }

    @Override
    public String toString() {
        return String.format("Sandwich: %s, Bread: %s, Size: %s, Toasted: %s, Toppings: %s, Price: $%.2f",
                getName(), breadType, size, toasted ? "Yes" : "No", String.join(", ", toppings), getPrice());
    }
}