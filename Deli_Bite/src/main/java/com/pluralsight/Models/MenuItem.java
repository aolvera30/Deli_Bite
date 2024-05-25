package com.pluralsight.Models;

public abstract class MenuItem
{
    private String name;
    private double price;

    public MenuItem(String name, double price)
    {
        this.name = name;
        this.price = price;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Item: %s, Price: $%.2f", name, price);
    }

}
