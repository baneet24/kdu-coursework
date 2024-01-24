package com.example.homework8.exceptions;

public class IdNotFoundException extends RuntimeException{
    public IdNotFoundException(String message){
        super(message);
    }
}
