package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BackPizza implements PizzaInterface {
    private List<Pizza> menu;
    private int nextId;


    public BackPizza() {
        this.menu = new ArrayList<>();
        this.nextId = 1;

    }


    @Override
    public void addPizza(Pizza pizza) {
        pizza.setId(nextId++);
        menu.add(pizza);
        System.out.println("ID" + pizza.getId() + "|Pizza " + pizza.getName() + " added to the menu");
    }

    @Override
    public void updatePrice(String pizzaName, double newPrice) {
        if (newPrice < 0) {
            System.out.println("Invalid price. Please enter a non-negative value.");
            return;
        }

        for (Pizza pizza : menu) {
            if (pizza.getName().equalsIgnoreCase(pizzaName)) {
                pizza.setPrice(newPrice);
                System.out.println("Price updated for " + pizzaName + ": KZT" + newPrice);
                return;
            }
        }
        System.out.println("Pizza " + pizzaName + " not found in the menu");
    }


    @Override
    public void deletePizza(String pizzaName) {
        Iterator<Pizza> iterator = menu.iterator();
        boolean pizzaFound = false;

        while (iterator.hasNext()) {
            Pizza pizza = iterator.next();

            if (pizza.getName().equalsIgnoreCase(pizzaName)) {
                iterator.remove();
                System.out.println("Pizza " + pizza.getName() + " removed from the menu");
                pizzaFound = true;
            }
        }

        if (!pizzaFound) {
            System.out.println("Pizza " + pizzaName + " not found on the menu");
        } else {
            updateIdAfterDeletion();
        }
    }

    private void updateIdAfterDeletion() {
        int newId = 1;
        for (Pizza pizza : menu) {
            pizza.setId(newId++);
        }
    }

    @Override
    public void viewAllPizzas() {
        System.out.println("+-----+------------------+------------+----------+----------------------+----------------+");
        System.out.printf("|%-5s| %-16s | %-10s | %-8s | %-20s | %-15s|\n", "ID", "Name", "Price(KZT)", "Size(cm)", "Toppings", "Base Type");
        System.out.println("+-----+------------------+------------+----------+----------------------+----------------+");
        for (Pizza pizza : menu) {
            System.out.printf("|%-5s| %-16s | %-10.2f | %-8s | %-20s | %-15s|\n",
                    pizza.getId(), pizza.getName(), pizza.getPrice(), pizza.getSize(), pizza.getGarniture(), pizza.getBasicType());
        }
        System.out.println("+-----+------------------+------------+----------+----------------------+----------------+");
    }


    public void displaySearchResults(List<Pizza> searchResults) {
        System.out.println("+-----+------------------+------------+----------+----------------------+----------------+");
        System.out.printf("|%-5s| %-16s | %-10s | %-8s | %-20s | %-15s|\n", "ID", "Name", "Price(KZT)", "Size(cm)", "Toppings", "Base Type");
        System.out.println("+-----+------------------+------------+----------+----------------------+----------------+");
        for (Pizza pizza : searchResults) {
            System.out.printf("|%-5s| %-16s | %-10.2f | %-8s | %-20s | %-15s|\n",
                    pizza.getId(), pizza.getName(), pizza.getPrice(), pizza.getSize(), pizza.getGarniture(), pizza.getBasicType());
        }
        System.out.println("+-----+------------------+------------+----------+----------------------+----------------+");
    }

    @Override
    public List<Pizza> searchPizza(String searchKey) {
        List<Pizza> searchResults = new ArrayList<>();

        for (Pizza pizza : menu) {
            if (pizza.getName().toLowerCase().contains(searchKey.toLowerCase()) || pizza.getSize().contains(searchKey)) {
                searchResults.add(pizza);
            }
        }


        return searchResults;
    }

    public double getPizzaPrice(String pizzaName) {
        for (Pizza pizza : menu) {
            if (pizza.getName().equalsIgnoreCase(pizzaName)) {
                return pizza.getPrice();
            }
        }
        return -1;
    }
}

