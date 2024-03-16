package org.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CustomerConsole {
    private BackPizza backPizza;
    private OrderHistory orderHistory;
    private double totalBill;
    private double balance;

    public CustomerConsole(BackPizza backPizza) {
        this.backPizza = backPizza;
        this.orderHistory = new OrderHistory();
        this.totalBill = 0.0;
        this.balance = 0.0;

    }

    public static String generateCard() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        for (int i = 1; i <= 16; i++) {
            int cardNum = random.nextInt(9);
            cardNumber.append(cardNum);

            if (i == 4 || i == 8 || i == 12) {
                cardNumber.append("-");
            }
        }

        return cardNumber.toString();
    }

    public void startCustomerConsole(BackPizza back) {
        Scanner scan = new Scanner(System.in);
        System.out.print("1. Enter your Full Name: ");
        String user = scan.nextLine();
        System.out.print("2. Enter your address: ");
        String userAddress = scan.nextLine();
        System.out.println("Hello " + user + "," + " your address " + userAddress);
        String cardNumber = generateCard();
        System.out.println("Your Bank Card Number(One Time): " + cardNumber);

        while (true) {
            System.out.println("Customer Console:");
            System.out.println("1.\t Place Order");
            System.out.println("2.\t Pay Bill");
            System.out.println("3.\t View All Pizzas");
            System.out.println("4.\t View Order History");
            System.out.println("5.\t Search Pizza");
            System.out.println("6.\t Bank Card Menu");
            System.out.println("7.\t Exit");
            System.out.print("||Select an option|| : ");

            try {
                int choice = scan.nextInt();
                scan.nextLine();
                switch (choice) {
                    case 1:
                        placeOrder(scan);
                        break;

                    case 2:
                        payBill(scan);
                        break;

                    case 3:
                        back.viewAllPizzas();
                        break;

                    case 4:
                        viewOrderHistory();
                        break;

                    case 5:
                        searchPizza(scan);
                        break;
                    case 6:
                        bankCardMenu(scan);
                        break;
                    case 7:
                        System.out.println("Goodbye Brat! Have a great day!");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
            }
        }
    }

    private void placeOrder(Scanner scan) {
        System.out.println("************************************");
        System.out.println("*           Place Order            *");
        System.out.println("************************************");
        System.out.println("Available Pizzas:");
        backPizza.viewAllPizzas();

        System.out.print("Enter the name of the pizza you want to order: ");
        String pizzaName = scan.nextLine();

        if (backPizza.getPizzaPrice(pizzaName) == -1) {
            System.out.println("Invalid pizza name. Please enter a valid pizza name.");
            return;
        }

        System.out.println("You can select only these sizes(cm): 25, 30, 35, 40");
        System.out.print("Choose one of them: ");
        String size = scan.nextLine();


        switch (size) {
            case "25":
                System.out.println("You selected a 25cm pizza.");

                break;
            case "30":
                System.out.println("You selected a 30cm pizza.");
                break;
            case "35":
                System.out.println("You selected a 35cm pizza.");
                break;
            case "40":
                System.out.println("You selected a 40cm pizza.");
                break;
            default:
                System.out.println("Invalid pizza size. Please choose from 25, 30, 35, or 40cm.");
                return;
        }
        double pizzaPrice = backPizza.getPizzaPrice(pizzaName);
        totalBill += pizzaPrice;


        Order order = new Order(pizzaName, size, pizzaPrice);
        this.orderHistory.addOrder(order);

        System.out.println("Order Details: " + order);
        System.out.print("Do you want to confirm the order? (y or n): ");
        String confirmOrder = scan.nextLine().toLowerCase();
        if (confirmOrder.equals("y")) {
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Order canceled.");
            totalBill -= pizzaPrice;
            this.orderHistory.removeLastOrder();
        }

    }

    private void payBill(Scanner scan) {
        System.out.println("************************************");
        System.out.println("*             Pay Bill              *");
        System.out.println("************************************");
        System.out.println();

        System.out.println("Total Bill(KZT): " + totalBill);
        System.out.println();
        if (totalBill > balance) {
            System.out.println("You have not enough money, you have " + balance + " KZT");
            System.out.println("Please top up your card");
            return;
        }

        System.out.println("Do you want to confirm the order? (y or n): ");
        String lastChoise = scan.nextLine().toLowerCase();
        if (lastChoise.equals("y")) {
            balance -= totalBill;
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Order canceled.");
            this.orderHistory.removeLastOrder();
        }

        System.out.println("Successful payment,you still have " + balance + " KZT");
        totalBill = 0;
    }

    private void viewOrderHistory() {
        System.out.println("************************************");
        System.out.println("*        View Order History         *");
        System.out.println("************************************");
        orderHistory.viewOrderHistory();
    }

    private void searchPizza(Scanner scan) {
        System.out.println("************************************");
        System.out.println("*          Search Pizza             *");
        System.out.println("************************************");
        System.out.print("Enter pizza name or size to search: ");
        String searchKey = scan.nextLine();
        List<Pizza> searchResults = backPizza.searchPizza(searchKey);

        if (searchResults.isEmpty()) {
            System.out.println("Sorry, pizzas not found.");
        } else {
            System.out.println("=== Search Results ===");
            backPizza.displaySearchResults(searchResults);
            System.out.println("======================");
        }
    }

    private void bankCardMenu(Scanner scan) {
        System.out.println("************************************");
        System.out.println("*          Bank Card Menu           *");
        System.out.println("************************************");
        System.out.println("1.\t Add money(KZT)");
        System.out.println("2.\t View Balance");
        System.out.println("3.\t Back to Customer Console");
        System.out.print("||Select an option|| : ");

        try {
            int bankChoice = scan.nextInt();
            scan.nextLine();
            switch (bankChoice) {
                case 1:
                    addMoney(scan);
                    break;

                case 2:
                    viewBalance();
                    break;

                case 3:
                    System.out.println("Returning to Customer Console");
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scan.nextLine();
        }
    }

    private void addMoney(Scanner scan) {
        System.out.print("Enter the amount to add to your balance(KZT): ");
        double amountToAdd = scan.nextDouble();
        scan.nextLine();

        if (amountToAdd < 0) {
            System.out.println("Please enter a positive value.");
            return;
        }

        balance += amountToAdd;
        System.out.println("New balance(KZT): " + balance);
    }

    private void viewBalance() {
        System.out.println("************************************");
        System.out.println("*            View Balance           *");
        System.out.println("************************************");
        System.out.println("Current Balance(KZT): " + balance);
    }
}
