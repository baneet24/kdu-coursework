package com.kdu.smarthome.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseRequestDTO {

    private String houseName;
    private String address;
}
