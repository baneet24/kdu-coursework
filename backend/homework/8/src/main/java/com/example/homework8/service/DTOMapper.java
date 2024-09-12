package com.example.homework8.service;

import com.example.homework8.dto.VehicleDTO;
import com.example.homework8.entity.Vehicle;

public class DTOMapper {
    private DTOMapper(){}
    public static VehicleDTO convertToDTO(Vehicle vehicle) {
        return new VehicleDTO(vehicle.getId(), vehicle.getName(), vehicle.getPrice());
    }

    public static Vehicle convertToEntity(VehicleDTO vehicleDTO) {
        return new Vehicle(vehicleDTO.getId(), vehicleDTO.getName(), vehicleDTO.getPrice());
    }
}