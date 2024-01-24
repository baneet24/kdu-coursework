package com.example.homework8.exceptions;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String message){
        super(message);
    }
}
