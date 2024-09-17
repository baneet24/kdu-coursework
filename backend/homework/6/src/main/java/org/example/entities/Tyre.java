package org.example.entities;

public class Tyre {
    private String brandName;

    private double price;

    public Tyre(String brandName, double price) {
        this.brandName = brandName;
        this.price = price;
    }

    public String getBrand() {
        return brandName;
    }

    public void setBrand(String brandName) {
        this.brandName = brandName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}