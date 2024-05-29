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

    public static void saveOrder(Order order) throws IOException {
        // Ensure the receipts folder exists
        File folder = new File(RECEIPTS_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Generate the filename with timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String filename = RECEIPTS_FOLDER + "/" + timestamp + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("*************************************");
            writer.newLine();
            writer.write("*            RECEIPT                *");
            writer.newLine();
            writer.write("*************************************");
            writer.newLine();
            writer.write("-------------------------------------");
            writer.newLine();
            writer.write(String.format("Order Date: %s", new SimpleDateFormat("yyyy-MM-dd").format(new Date())));
            writer.newLine();
            writer.write(String.format("Time: %s", new SimpleDateFormat("HH:mm:ss").format(new Date())));
            writer.newLine();
            writer.write("-------------------------------------");
            writer.newLine();

            for (MenuItem item : order.getItems()) {
                writer.write(formatItemString(item));
                writer.newLine();
                writer.write("-------------------------------------");
                writer.newLine();
            }

            writer.write(String.format("Total Price: $%.2f", order.getTotalPrice()));
            writer.newLine();
            writer.write("-------------------------------------");
            writer.newLine();
            writer.write("Thank you for your order!");
            writer.newLine();
            writer.write("*************************************");
        }

        System.out.println("Order saved to file: " + filename);
    }

    private static String formatItemString(MenuItem item) {
        if (item instanceof Sandwich) {
            Sandwich sandwich = (Sandwich) item;
            StringBuilder sb = new StringBuilder();
            sb.append("Sandwich:\n");
            sb.append("  Name: ").append(sandwich.getName()).append("\n");
            sb.append("  Bread: ").append(sandwich.getBreadType()).append("\n");
            sb.append("  Size: ").append(sandwich.getSize()).append("\n");
            sb.append("  Toasted: ").append(sandwich.isToasted() ? "Yes" : "No").append("\n");
            sb.append("  Toppings:\n");
            for (String topping : sandwich.getToppings()) {
                sb.append("    - ").append(topping).append("\n");
            }
            sb.append("  Price: $").append(String.format("%.2f", sandwich.getPrice()));
            return sb.toString();
        } else if (item instanceof Drink) {
            Drink drink = (Drink) item;
            return String.format("Drink:\n  Name: %s\n  Size: %s\n  Price: $%.2f", drink.getName(), drink.getSize(), drink.getPrice());
        } else if (item instanceof Chips) {
            Chips chips = (Chips) item;
            return String.format("Chips:\n  Name: %s\n  Price: $%.2f", chips.getName(), chips.getPrice());
        }
        return "";
    }

    private static MenuItem stringToItem(String str) {
        String[] lines = str.split("\n");
        String type = lines[0].trim();

        switch (type) {
            case "Sandwich:":
                String name = lines[1].split(":")[1].trim();
                String breadType = lines[2].split(":")[1].trim();
                String size = lines[3].split(":")[1].trim();
                boolean toasted = lines[4].split(":")[1].trim().equalsIgnoreCase("Yes");
                List<String> toppings = Arrays.asList(lines[5].split(":")[1].trim().split(", "));
                double price = Double.parseDouble(lines[6].split(":")[1].trim().replace("$", ""));
                return new Sandwich(name, breadType, size, toasted, toppings, price);
            case "Drink:":
                name = lines[1].split(":")[1].trim();
                String drinkSize = lines[2].split(":")[1].trim();
                double drinkPrice = Double.parseDouble(lines[3].split(":")[1].trim().replace("$", ""));
                return new Drink(name, drinkSize, drinkPrice);
            case "Chips:":
                name = lines[1].split(":")[1].trim();
                double chipsPrice = Double.parseDouble(lines[2].split(":")[1].trim().replace("$", ""));
                return new Chips(name, chipsPrice);
            default:
                return null;
        }
    }


    public static List<Order> loadOrders() throws IOException {
        List<Order> orders = new ArrayList<>();
        File folder = new File(RECEIPTS_FOLDER);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files != null) {
            System.out.println("Found " + files.length + " receipt files.");
            for (File file : files) {
                System.out.println("Loading file: " + file.getName());
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    Order order = new Order();
                    StringBuilder itemData = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.trim().equals("===================")) {
                            if (itemData.length() > 0) {
                                MenuItem item = stringToItem(itemData.toString().trim());
                                if (item != null) {
                                    order.addItem(item);
                                }
                                itemData.setLength(0);
                            }
                        } else {
                            itemData.append(line).append("\n");
                        }
                    }
                    if (!order.getItems().isEmpty()) {
                        orders.add(order);
                    }
                } catch (IOException e) {
                    System.out.println("Error reading file: " + file.getName());
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No receipt files found.");
        }

        System.out.println("Loaded " + orders.size() + " orders.");
        return orders;
    }
}
