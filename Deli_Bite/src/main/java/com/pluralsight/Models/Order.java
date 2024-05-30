package com.pluralsight.Models;


import com.pluralsight.UserInterface.Colors;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderName;
    private List<MenuItem> items;
    private double totalPrice;

    public Order(String orderName) {
        this.orderName = orderName;
        items = new ArrayList<>();
        totalPrice = 0.0;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    // Method to add an item to the order
    public void addItem(MenuItem item) {
        items.add(item);
        totalPrice += item.getPrice();
    }

    // Method to display order details
    public void displayOrderDetails() {
        System.out.println(Colors.GREEN + "----------------------------");
        for (MenuItem item : items) {
            System.out.println(item);
        }
        System.out.printf("Total Price: " + Colors.GREEN + "$%.2f" + Colors.RESET + "%n", getTotalPrice());
        System.out.println(Colors.GREEN + "----------------------------" + Colors.RESET);
    }

    // Method to calculate the total price of the order
    public double calculateTotalPrice() {
        totalPrice = 0.0;
        for (MenuItem item : items) {
            totalPrice += item.getPrice();
        }
        return totalPrice;
    }

}