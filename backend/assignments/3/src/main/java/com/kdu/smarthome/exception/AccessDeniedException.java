package com.kdu.smarthome.exception;

public class AccessDeniedException extends RuntimeException{
    public AccessDeniedException(String message){
        super(message);
    }
}
