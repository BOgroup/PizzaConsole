package org.example;

import java.util.ArrayList;
import java.util.List;

public class OrderHistory {
    private List<Order> orders;

    public OrderHistory() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeLastOrder() {
        if (!orders.isEmpty()) {
            orders.remove(orders.size() - 1);
        }
    }


    public void viewOrderHistory() {
        System.out.println("=== Order History ===");
        for (Order order : orders) {
            System.out.println(order);
        }
        System.out.println("=====================");
    }
}
