package com.pluralsight.Models;

import java.util.List;

public class Sandwich extends MenuItem
{
    private String breadType;
    private String size;
    private boolean toasted;
    private List<String> toppings;

    // Constants for base prices and topping prices
    private static final double BASE_PRICE_4 = 5.50;
    private static final double BASE_PRICE_8 = 7.00;
    private static final double BASE_PRICE_12 = 8.50;

    private static final double MEAT_PRICE_4 = 1.00;
    private static final double MEAT_PRICE_8 = 2.00;
    private static final double MEAT_PRICE_12 = 3.00;

    private static final double CHEESE_PRICE_4 = 0.75;
    private static final double CHEESE_PRICE_8 = 1.50;
    private static final double CHEESE_PRICE_12 = 2.25;

    private static final double EXTRA_MEAT_PRICE_4 = 0.50;
    private static final double EXTRA_MEAT_PRICE_8 = 1.00;
    private static final double EXTRA_MEAT_PRICE_12 = 1.50;

    private static final double EXTRA_CHEESE_PRICE_4 = 0.30;
    private static final double EXTRA_CHEESE_PRICE_8 = 0.60;
    private static final double EXTRA_CHEESE_PRICE_12 = 0.90;


    public Sandwich(String name, double price, String breadType, String size, boolean toasted, List<String> toppings){
        super(name, 0); // Initial price is set to 0, it will be calculated dynamically
        this.breadType = breadType;
        this.size = size;
        this.toasted = toasted;
        this.toppings = toppings;
    }

    public String getBreadType()
    {
        return breadType;
    }

    public void setBreadType(String breadType)
    {
        this.breadType = breadType;
    }

    public String getSize()
    {
        return size;
    }

    public void setSize(String size)
    {
        this.size = size;
    }

    public boolean isToasted()
    {
        return toasted;
    }

    public void setToasted(boolean toasted)
    {
        this.toasted = toasted;
    }

    public List<String> getToppings()
    {
        return toppings;
    }

    public void setToppings(List<String> toppings)
    {
        this.toppings = toppings;
    }

    @Override
    public double getPrice() {
        double basePrice = 0;
        switch (size) {
            case "4\"":
                basePrice = BASE_PRICE_4;
                break;
            case "8\"":
                basePrice = BASE_PRICE_8;
                break;
            case "12\"":
                basePrice = BASE_PRICE_12;
                break;
        }

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
                    meatPrice += (size.equals("4\"") ? MEAT_PRICE_4 : size.equals("8\"") ? MEAT_PRICE_8 : MEAT_PRICE_12);
                    break;
                case "extra meat":
                    extraMeatPrice += (size.equals("4\"") ? EXTRA_MEAT_PRICE_4 : size.equals("8\"") ? EXTRA_MEAT_PRICE_8 : EXTRA_MEAT_PRICE_12);
                    break;
                case "american":
                case "provolone":
                case "cheddar":
                case "swiss":
                    cheesePrice += (size.equals("4\"") ? CHEESE_PRICE_4 : size.equals("8\"") ? CHEESE_PRICE_8 : CHEESE_PRICE_12);
                    break;
                case "extra cheese":
                    extraCheesePrice += (size.equals("4\"") ? EXTRA_CHEESE_PRICE_4 : size.equals("8\"") ? EXTRA_CHEESE_PRICE_8 : EXTRA_CHEESE_PRICE_12);
                    break;

            }
        }

        return basePrice + meatPrice + cheesePrice + extraMeatPrice + extraCheesePrice;
    }

    @Override
    public String toString() {
        return String.format("Sandwich: %s, Bread: %s, Size: %s, Toasted: %s, Toppings: %s, Price: $%.2f",
                getName(), breadType, size, toasted ? "Yes" : "No", toppings, getPrice());
    }

}
