package com.kdu.smarthome.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class DeviceInventory {

    @Id
    @Column(length = 6)
    private String kickstonId;

    private String deviceUsername;

    private String devicePassword;

    private LocalDate manufacturerDateTime;

    private String manufacturerFactoryPlace;

    private boolean available;
}