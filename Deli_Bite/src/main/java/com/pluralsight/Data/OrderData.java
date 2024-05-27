package com.pluralsight.Data;

import com.pluralsight.Models.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OrderData {

    private static final String RECEIPTS_FOLDER = "receipts";

    // Method to save an order to a file
    public static void saveOrder(Order order) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String filename = RECEIPTS_FOLDER + "/" + timestamp + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (MenuItem item : order.getItems()) {
                writer.write(itemToString(item));
                writer.newLine();
            }
            writer.write("TotalPrice:" + order.getTotalPrice());
            writer.newLine();
        }
    }

    // Method to load all orders from the receipts folder
    public static List<Order> loadOrders() throws IOException {
        List<Order> orders = new ArrayList<>();
        File folder = new File(RECEIPTS_FOLDER);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files != null) {
            for (File file : files) {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    Order order = new Order();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("TotalPrice:")) {
                            // Ignore total price line, it will be recalculated
                        } else {
                            MenuItem item = stringToItem(line);
                            if (item != null) {
                                order.addItem(item);
                            }
                        }
                    }
                    if (!order.getItems().isEmpty()) {
                        orders.add(order);
                    }
                }
            }
        }
        return orders;
    }

    // Helper method to convert a MenuItem to a string
    private static String itemToString(MenuItem item) {
        if (item instanceof Sandwich) {
            Sandwich sandwich = (Sandwich) item;
            return String.format("Sandwich:%s,%s,%s,%b,%s,%.2f",
                    sandwich.getName(), sandwich.getBreadType(), sandwich.getSize(), sandwich.isToasted(), String.join(";", sandwich.getToppings()), sandwich.getPrice());
        } else if (item instanceof Drink) {
            Drink drink = (Drink) item;
            return String.format("Drink:%s,%s,%.2f", drink.getName(), drink.getSize(), drink.getPrice());
        } else if (item instanceof Chips) {
            Chips chips = (Chips) item;
            return String.format("Chips:%s,%.2f", chips.getName(), chips.getPrice());
        }
        return "";
    }

    // Helper method to convert a string to a MenuItem
    private static MenuItem stringToItem(String str) {
        String[] parts = str.split(":");
        String type = parts[0];
        String[] attributes = parts[1].split(",");
        switch (type) {
            case "Sandwich":
                String name = attributes[0];
                String breadType = attributes[1];
                String size = attributes[2];
                boolean toasted = Boolean.parseBoolean(attributes[3]);
                List<String> toppings = Arrays.asList(attributes[4].split(";"));
                double price = Double.parseDouble(attributes[5]);
                return new Sandwich(name, price, breadType, size, toasted, toppings);
            case "Drink":
                return new Drink(attributes[0], attributes[1], Double.parseDouble(attributes[2]));
            case "Chips":
                return new Chips(attributes[0], Double.parseDouble(attributes[1]));
            default:
                return null;
        }
    }
}