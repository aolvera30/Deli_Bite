package com.pluralsight.UserInterface;

import com.pluralsight.Data.OrderData;
import com.pluralsight.Models.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private static Scanner userInput = new Scanner(System.in);

    private void showHomeScreen() {
        System.out.println(Colors.GREEN + "\n*****************************");
        System.out.println("*         DELI BITE         *");
        System.out.println("*****************************" + Colors.RESET);
        System.out.println(Colors.YELLOW + "*****************************" + Colors.RESET);
        System.out.println(Colors.YELLOW + "*      Home Screen         *" + Colors.RESET);
        System.out.println(Colors.YELLOW + "*****************************" + Colors.RESET);
        System.out.println(Colors.RED + "* 1. New Order             *" + Colors.RESET);
        System.out.println(Colors.RED + "* 0. Exit                  *" + Colors.RESET);
        System.out.println(Colors.YELLOW + "*****************************" + Colors.RESET);
        System.out.print("Enter your choice: ");
    }

    public void start() {
        while (true) {
            showHomeScreen();
            String choice = userInput.nextLine();
            if (choice.equals("1")) {
                takeNewOrder();
            } else if (choice.equals("0")) {
                System.out.println(Colors.YELLOW + "Exiting the application..." + Colors.RESET);
                break;
            } else {
                System.out.println(Colors.RED + "Invalid choice. Please try again." + Colors.RESET);
            }
        }
    }

    private void showOrderScreen() {
        System.out.println(Colors.YELLOW + "\n*****************************");
        System.out.println("*      Order Screen         *");
        System.out.println("*****************************" + Colors.RESET);
        System.out.println(Colors.RED + "* 1. Add Sandwich           *" + Colors.RESET);
        System.out.println(Colors.RED + "* 2. Add Drink              *" + Colors.RESET);
        System.out.println(Colors.RED + "* 3. Add Chips              *" + Colors.RESET);
        System.out.println(Colors.RED + "* 4. Checkout               *" + Colors.RESET);
        System.out.println(Colors.RED + "* 0. Cancel Order           *" + Colors.RESET);
        System.out.println(Colors.YELLOW + "*****************************" + Colors.RESET);
        System.out.print("Enter your choice: ");
    }

    private void takeNewOrder() {
        Order order = new Order();

        while (true) {
            showOrderScreen();
            String choice = userInput.nextLine();
            switch (choice) {
                case "1":
                    order.addItem(addSandwich());
                    break;
                case "2":
                    order.addItem(addDrink());
                    break;
                case "3":
                    order.addItem(addChips());
                    break;
                case "4":
                    if (confirmCheckout(order)) {
                        return; // Finish order
                    }
                    break;
                case "0":
                    System.out.println(Colors.YELLOW + "Order cancelled." + Colors.RESET);
                    return; // Cancel order
                default:
                    System.out.println(Colors.RED + "Invalid choice. Please try again." + Colors.RESET);
            }
        }
    }

    private Sandwich addSandwich() {
        System.out.print(Colors.YELLOW + "Order Name: " + Colors.RESET);
        String name = userInput.nextLine();

        System.out.print(Colors.YELLOW + "Select your bread (white, wheat, rye, wrap): " + Colors.RESET);
        String breadType = userInput.nextLine();

        System.out.print(Colors.YELLOW + "Select sandwich size (4\", 8\", 12\"): " + Colors.RESET);
        String size = userInput.nextLine();

        System.out.print(Colors.YELLOW + "Would you like the sandwich toasted? (yes/no): " + Colors.RESET);
        boolean toasted = userInput.nextLine().equalsIgnoreCase("yes");

        System.out.println(Colors.YELLOW + "Available meat toppings: steak, ham, salami, roast beef, chicken, bacon" + Colors.RESET);
        System.out.print(Colors.YELLOW + "Enter meat toppings (comma separated, or 'none'): " + Colors.RESET);
        List<String> meatToppings = Arrays.asList(userInput.nextLine().split(","));

        boolean extraMeat = false;
        if (!meatToppings.contains("none")) {
            System.out.print(Colors.YELLOW + "Would you like extra meat? (yes/no): " + Colors.RESET);
            extraMeat = userInput.nextLine().equalsIgnoreCase("yes");
        } else {
            meatToppings = new ArrayList<>(); // Clear meat toppings if 'none' was selected
        }

        System.out.println(Colors.YELLOW + "Available cheese toppings: american, provolone, cheddar, swiss" + Colors.RESET);
        System.out.print(Colors.YELLOW + "Enter cheese toppings (comma separated, or 'none'): " + Colors.RESET);
        List<String> cheeseToppings = Arrays.asList(userInput.nextLine().split(","));

        boolean extraCheese = false;
        if (!cheeseToppings.contains("none")) {
            System.out.print(Colors.YELLOW + "Would you like extra cheese? (yes/no): " + Colors.RESET);
            extraCheese = userInput.nextLine().equalsIgnoreCase("yes");
        } else {
            cheeseToppings = new ArrayList<>(); // Clear cheese toppings if 'none' was selected
        }

        System.out.println(Colors.YELLOW + "Available regular toppings: lettuce, peppers, onions, tomatoes, jalapenos, cucumbers, pickles, guacamole, mushrooms" + Colors.RESET);
        System.out.print(Colors.YELLOW + "Enter regular toppings (comma separated, or 'none'): " + Colors.RESET);
        List<String> regularToppings = Arrays.asList(userInput.nextLine().split(","));
        if (regularToppings.contains("none")) {
            regularToppings = new ArrayList<>(); // Clear regular toppings if 'none' was selected
        }

        System.out.println(Colors.YELLOW + "Available sauce toppings: mayo, mustard, ketchup, ranch, thousand islands, vinaigrette" + Colors.RESET);
        System.out.print(Colors.YELLOW + "Enter sauce toppings (comma separated, or 'none'): " + Colors.RESET);
        List<String> sauceToppings = Arrays.asList(userInput.nextLine().split(","));
        if (sauceToppings.contains("none")) {
            sauceToppings = new ArrayList<>(); // Clear sauce toppings if 'none' was selected
        }

        // Combine all toppings into one list
        List<String> allToppings = new ArrayList<>();
        allToppings.addAll(meatToppings);
        allToppings.addAll(cheeseToppings);
        allToppings.addAll(regularToppings);
        allToppings.addAll(sauceToppings);

        // Add extra meat and cheese toppings
        if (extraMeat) {
            allToppings.add("extra meat");
        }
        if (extraCheese) {
            allToppings.add("extra cheese");
        }

        Sandwich sandwich = new Sandwich(name, breadType, size, toasted, allToppings);

        double basePrice = sandwich.getBasePrice();
        double meatPrice = sandwich.getToppingPrice(meatToppings, Sandwich.MEAT_PRICE_4, Sandwich.MEAT_PRICE_8, Sandwich.MEAT_PRICE_12);
        double cheesePrice = sandwich.getToppingPrice(cheeseToppings, Sandwich.CHEESE_PRICE_4, Sandwich.CHEESE_PRICE_8, Sandwich.CHEESE_PRICE_12);
        double extraMeatPrice = extraMeat ? sandwich.getToppingPrice(Arrays.asList("extra meat"), Sandwich.EXTRA_MEAT_PRICE_4, Sandwich.EXTRA_MEAT_PRICE_8, Sandwich.EXTRA_MEAT_PRICE_12) : 0;
        double extraCheesePrice = extraCheese ? sandwich.getToppingPrice(Arrays.asList("extra cheese"), Sandwich.EXTRA_CHEESE_PRICE_4, Sandwich.EXTRA_CHEESE_PRICE_8, Sandwich.EXTRA_CHEESE_PRICE_12) : 0;
        double totalPrice = basePrice + meatPrice + cheesePrice + extraMeatPrice + extraCheesePrice;

        System.out.printf(Colors.RED + "Base Price: $%.2f, Meat Price: $%.2f, Cheese Price: $%.2f, Extra Meat Price: $%.2f, Extra Cheese Price: $%.2f, Total Price: $%.2f\n" + Colors.RESET,
                basePrice, meatPrice, cheesePrice, extraMeatPrice, extraCheesePrice, totalPrice);

        sandwich.setPrice(totalPrice);

        return sandwich;
    }

    private Drink addDrink() {
        System.out.print(Colors.YELLOW + "Enter drink selection (Coke, Orange Fanta, Lemonade): " + Colors.RESET);
        String name = userInput.nextLine();

        System.out.print(Colors.YELLOW + "Select drink size (small, medium, large): " + Colors.RESET);
        String size = userInput.nextLine();

        double price;
        switch (size.toLowerCase()) {
            case "small":
                price = 2.00;
                break;
            case "medium":
                price = 2.50;
                break;
            case "large":
                price = 3.00;
                break;
            default:
                price = 0;
        }

        return new Drink(name, size, price);
    }

    private Chips addChips() {
        System.out.print(Colors.YELLOW + "Enter chip selection (Doritos, Hot Cheetos, Lays, or Ruffles): " + Colors.RESET);
        String name = userInput.nextLine();

        double price = 1.50; // Fixed price for chips

        return new Chips(name, price);
    }

    private boolean confirmCheckout(Order order) {
        order.displayOrderDetails();
        System.out.print(Colors.YELLOW + "Confirm checkout? (yes/no): " + Colors.RESET);
        boolean confirmed = userInput.nextLine().equalsIgnoreCase("yes");

        if (confirmed) {
            try {
                OrderData.saveOrder(order);
                System.out.println(Colors.RED + "Order saved successfully." + Colors.RESET);
            } catch (IOException e) {
                System.out.println(Colors.RED + "Error saving order: " + e.getMessage() + Colors.RESET);
            }
        }

        return confirmed;
    }
}