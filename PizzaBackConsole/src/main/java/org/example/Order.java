package org.example;

public class Order {
    private String pizzaName;
    private String size;
    private double price;

    public Order(String pizzaName, String size, double price) {
        this.pizzaName = pizzaName;
        this.size = size;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Pizza: " + pizzaName + ", Size: " + size + ", Price: KZT" + price;
    }
}

