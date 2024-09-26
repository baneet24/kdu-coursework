package com.example.homework9.dto;
import lombok.Data;

@Data
public class RequestDTO {
    int id;
    String name;
    String color;

    public RequestDTO(int id, String name, String color){
        this.id = id;
        this.name = name;
        this.color = color;
    }
}
