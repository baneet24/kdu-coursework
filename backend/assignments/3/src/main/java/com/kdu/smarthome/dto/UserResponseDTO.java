package com.kdu.smarthome.dto;
import com.kdu.smarthome.model.Person;
import jakarta.servlet.http.PushBuilder;
import lombok.Data;

@Data
public class UserResponseDTO {
    private String message;
    private Person person;

    public UserResponseDTO(String message, Person person) {
        this.message = message;
        this.person = person;
    }
}