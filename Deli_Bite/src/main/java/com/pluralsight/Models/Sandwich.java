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
    public String getDetails() {
        return String.format("Sandwich: %s, Bread: %s, Size: %s, Toasted: %s, Toppings: %s, Price: $%.2f",
                getName(), breadType, size, toasted ? "Yes" : "No", toppings, getPrice());
    }
}
