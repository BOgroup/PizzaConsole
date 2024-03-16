package org.example;

public class InvalidPizzaException extends Exception {
    public InvalidPizzaException(String message) {
        super(message);
    }
}