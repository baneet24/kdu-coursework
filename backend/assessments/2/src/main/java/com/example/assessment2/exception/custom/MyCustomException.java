package com.example.assessment2.exception.custom;

public class MyCustomException extends IndexOutOfBoundsException{
    public MyCustomException(String s) {
        super(s);
    }
}
