package com.kdu.smarthome.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class InventoryRequestDTO {
    private String kickstonId;
    private String deviceUsername;
    private String devicePassword;
    private LocalDate manufacturerDateTime;
    private String manufacturerFactoryPlace;

}
