package com.kdu.smarthome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponseDTO {

    private String message;
    private String object;
    private HttpStatus httpStatus;
}