package com.kdu.smarthome.exception;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(String roomNotFound) {
        super(roomNotFound);
    }
}
