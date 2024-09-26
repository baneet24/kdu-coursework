package com.example.homework9.dto;
import lombok.Data;

@Data
public class ResponseDTO {
    int id;
    double price;

    public ResponseDTO(int id, double price){
        this.id = id;
        this.price = price;
    }
}
