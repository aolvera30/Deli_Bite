package com.pluralsight.Models;

public abstract class MenuItem {
    private String item;
    private double price;

    public MenuItem(String item, double price) {
        this.item = item;
        this.price = price;
    }

    public String getItem() {
        return item;
    }


    public double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return String.format("Item: %s, Price: $%.2f", item, price);
    }
}
