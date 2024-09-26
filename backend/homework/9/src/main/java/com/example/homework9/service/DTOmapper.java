package com.example.homework9.service;

import com.example.homework9.Constants;
import com.example.homework9.dto.RequestDTO;
import com.example.homework9.dto.ResponseDTO;
import com.example.homework9.entity.Vehicle;

public class DTOmapper{
    private DTOmapper(){}

    public static Vehicle requestDTOToVehicle(RequestDTO vehicleDTO){
        return new Vehicle(vehicleDTO.getId(), vehicleDTO.getName(), Constants.VEHICLE_PRICE);
    }

    public static ResponseDTO vehicleToVehicleResponseDTO(Vehicle vehicle){
        return new ResponseDTO(vehicle.getId(), vehicle.getPrice());
    }
}
