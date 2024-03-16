package org.example;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Log {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BackPizza back = new BackPizza();
        CustomerConsole custom = new CustomerConsole(back);

        while (true) {
            System.out.println("1.\t Admin Console");
            System.out.println("2.\t Customer Console");
            System.out.println("3.\t Exit");
            System.out.println();
            System.out.print("||Select an option|| : ");
            int firstChoice;

            try {
                firstChoice = scan.nextInt();
                scan.nextLine();

                if (firstChoice == 1) {
                    adminConsole(scan, back);
                } else if (firstChoice == 2) {
                    custom.startCustomerConsole(back);
                } else if (firstChoice == 3) {
                    System.out.println("Bye-Bye Brat!");
                    System.exit(0);
                } else {
                    System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
            }
        }
    }

    private static void adminConsole(Scanner scan, BackPizza back) {
        while (true) {
            System.out.println("Admin Console:");
            System.out.println("1.\t Add New Pizza");
            System.out.println("2.\t Update Price");
            System.out.println("3.\t Delete Pizza");
            System.out.println("4.\t View All Pizzas");
            System.out.println("5.\t Search pizza");
            System.out.println("6.\t Back to Main Menu");
            System.out.println("7.\t Exit");
            System.out.print("||Select an option|| : ");

            try {
                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        addNewPizza(scan, back);
                        break;

                    case 2:
                        updatePizzaPrice(scan, back);
                        break;

                    case 3:
                        deletePizza(scan, back);
                        break;

                    case 4:
                        back.viewAllPizzas();
                        break;

                    case 5:
                        searchPizza(scan, back);
                        break;

                    case 6:
                        System.out.println("Main Menu");
                        return;

                    case 7:
                        System.out.println("Bye-Bye Brat!");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number." + e);
                scan.nextLine();
            }
        }
    }

    private static void addNewPizza(Scanner scan, BackPizza back) {
        try {
            System.out.println("************************************");
            System.out.println("*           Add New Pizza           *");
            System.out.println("************************************");
            System.out.print("Enter Pizza Name(16): ");
            String name = scan.nextLine();
            System.out.print("Enter Pizza Price(KZT)(10): ");
            double price = scan.nextDouble();
            if (price < 0) {
                throw new InvalidPizzaException("Invalid price. Please enter a positive value.");
            }
            scan.nextLine();
            System.out.print("Enter Pizza Size! (8) ");
            System.out.println("|ADVICE| You can select only these sizes (cm): 25, 30, 35, 40");

            String[] allowedSizes = {"25", "30", "35", "40"};
            String size;

            do {
                System.out.print("Enter Pizza size:");
                size = scan.nextLine();

                if (!Arrays.asList(allowedSizes).contains(size)) {
                    System.out.println("|WARN|Please choose from allowed sizes.");
                }
            } while (!Arrays.asList(allowedSizes).contains(size));

            System.out.print("Enter Pizza Garniture(20): ");
            List<String> garniture = List.of(scan.nextLine().split(","));
            System.out.print("Enter Pizza Base Type(15): ");
            String baseType = scan.nextLine();
            int id = 1;

            Pizza newPizza = new Pizza(id, name, price, size, garniture, baseType);
            back.addPizza(newPizza);
        } catch (InvalidPizzaException e) {
            System.out.println(e.getMessage());
            return;
        }
    }


    private static void updatePizzaPrice(Scanner scan, BackPizza back) {
        System.out.println("************************************");
        System.out.println("*         Update Pizza Price        *");
        System.out.println("************************************");
        System.out.print("Enter Pizza name to update price: ");
        String updateName = scan.nextLine();
        System.out.print("Enter new Price(KZT): ");
        double newPrice = scan.nextDouble();
        back.updatePrice(updateName, newPrice);
    }

    private static void deletePizza(Scanner scan, BackPizza back) {
        System.out.println("************************************");
        System.out.println("*          Delete Pizza             *");
        System.out.println("************************************");

        System.out.print("Enter Pizza name to delete(IGNORE CASES): ");
        String deleteName = scan.nextLine();
        System.out.print("Are you sure you want to delete the pizza? (y or n): ");
        String confirmDelete = scan.nextLine().toLowerCase();
        if (confirmDelete.equals("y")) {
            back.deletePizza(deleteName);
        } else {
            System.out.println("Deletion canceled.");
        }
    }

    private static void searchPizza(Scanner scan, BackPizza back) {
        System.out.println("************************************");
        System.out.println("*          Search Pizza             *");
        System.out.println("************************************");
        System.out.print("Enter pizza name, or size to search: ");
        String searchKey = scan.nextLine();
        List<Pizza> searchResults = back.searchPizza(searchKey);

        if (searchResults.isEmpty()) {
            System.out.println("Does not exist such Pizza");
        } else {
            System.out.println("=== Search Results ===");
            back.displaySearchResults(searchResults);
            System.out.println("======================");
        }
    }
}
