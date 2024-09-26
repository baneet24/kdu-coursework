package com.example.homework9.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorDTO {
    String message;
    private HttpStatus code;
}
