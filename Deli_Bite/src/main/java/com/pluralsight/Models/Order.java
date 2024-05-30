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
        this.items = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    public String getOrderName() {
        return orderName;
    }


    public List<MenuItem> getItems() {
        return items;
    }

    public double getTotalPrice() {
        return totalPrice;
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
}

