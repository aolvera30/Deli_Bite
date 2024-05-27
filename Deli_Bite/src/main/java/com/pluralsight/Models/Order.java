package com.pluralsight.Models;


import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<MenuItem> items;
    private double totalPrice;

    public Order() {
        items = new ArrayList<>();
        totalPrice = 0.0;
    }

    // Method to add an item to the order
    public void addItem(MenuItem item) {
        items.add(item);
        totalPrice += item.getPrice();
    }

    // Method to calculate the total price of the order
    public double calculateTotalPrice() {
        totalPrice = 0.0;
        for (MenuItem item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

    // Getters
    public List<MenuItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // Method to display order details
    public void displayOrderDetails() {
        for (MenuItem item : items) {
            System.out.println(item);
        }
        System.out.printf("Total Price: $%.2f%n", calculateTotalPrice());
    }
}