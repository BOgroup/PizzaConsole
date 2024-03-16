package org.example;

import java.util.List;

public class Pizza {
    private int id;
    private String name;
    private double price;
    private String size;
    private List<String> garniture;
    private String basicType;

    public String getName() {
        return name;
    }


    public double getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }


    public List<String> getGarniture() {
        return garniture;
    }

    public String getBasicType() {
        return basicType;
    }

    public int getId() {
        return id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Pizza(int id, String name, double price, String size, List<String> garniture, String basicType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size = size;
        this.garniture = garniture;
        this.basicType = basicType;
    }
}
