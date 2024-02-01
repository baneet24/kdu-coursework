package com.example.jpahw.exceptions.customexceptions;

public class NotFoundException extends Throwable {
    public NotFoundException(String message) {
        super(message);
    }
}