package org.example;

import java.util.List;

public interface PizzaInterface {
    void addPizza(Pizza pizza);

    void updatePrice(String pizzaName, double newPrice);

    void deletePizza(String pizzaName);


    void viewAllPizzas();

    List<Pizza> searchPizza(String searchKey);

    double getPizzaPrice(String pizzaName);

}
