package com.example.homework8.repository;

import com.example.homework8.dto.VehicleDTO;
import com.example.homework8.exceptions.VehicleNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.homework8.Constants.VEHICLE_EXCEPTION_MESSAGE;

@Repository
public class FactoryInventory {
    private final List<VehicleDTO> inventory = new ArrayList<>();

    public List<VehicleDTO> getAllVehicles(){
        return inventory;
    }

    public void addVehicle(VehicleDTO vehicle) {
        if(vehicle == null){
            throw new VehicleNotFoundException(VEHICLE_EXCEPTION_MESSAGE);
        }
        else if (!inventory.contains(vehicle)) {
            inventory.add(vehicle);
        }
    }

    public void deleteVehicle(VehicleDTO vehicle) {
        if (vehicle != null) {
            inventory.remove(vehicle);
        }
        else{
            throw new VehicleNotFoundException(VEHICLE_EXCEPTION_MESSAGE);
        }
    }

    public Optional<VehicleDTO> retrieveVehicle(int id) {
        return inventory.stream()
                .filter(v -> v.getId() == id)
                .findFirst();
    }


    public void updateVehicle(double id, VehicleDTO updatedVehicle) {
        if (updatedVehicle != null) {
            inventory.stream()
                    .filter(existingVehicle -> existingVehicle.getId() == (id))
                    .findFirst()
                    .ifPresent(existingVehicle -> {
                        existingVehicle.setName(updatedVehicle.getName());
                        existingVehicle.setPrice(updatedVehicle.getPrice());
                    });
        }
        else{
            throw new VehicleNotFoundException(VEHICLE_EXCEPTION_MESSAGE);
        }
    }
}