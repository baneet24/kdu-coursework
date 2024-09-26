package com.example.homework9.entity;
import lombok.Data;

@Data
public class Vehicle {
    private int id;
    private String name;
    private double price;

    public Vehicle(int id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
