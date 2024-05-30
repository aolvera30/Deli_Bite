package com.pluralsight.Data;

import com.pluralsight.Models.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


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
            writer.write(String.format("Order Name: %s", order.getOrderName()));
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
            sb.append("Item: Sandwich\n");
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
            return String.format("Item: Drink\n  Selection: %s\n  Size: %s\n  Price: $%.2f", drink.getItem(), drink.getSize(), drink.getPrice());
        } else if (item instanceof Chips) {
            Chips chips = (Chips) item;
            return String.format("Item: Chips\n  Selection: %s\n  Price: $%.2f", chips.getItem(), chips.getPrice());
        }
        return "";
    }

}
