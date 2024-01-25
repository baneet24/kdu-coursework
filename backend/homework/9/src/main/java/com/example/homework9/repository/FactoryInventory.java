package com.example.homework9.repository;

import com.example.homework9.entity.Vehicle;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class FactoryInventory {
    private final List<Vehicle> inventory = new ArrayList<>();

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public void addVehicle(Vehicle vehicle) {
            inventory.add(vehicle);
    }

    public void deleteVehicle(Vehicle vehicle) {
            inventory.remove(vehicle);
    }

    public Optional<Vehicle> retrieveVehicle(int id) {
        return inventory.stream()
                .filter(v -> v.getId() == id)
                .findFirst();
    }

    public void updateVehicle(double id, Vehicle updatedVehicle) {
            inventory.stream()
                    .filter(existingVehicle -> existingVehicle.getId() == (id))
                    .findFirst()
                    .ifPresent(existingVehicle -> {
                        existingVehicle.setName(updatedVehicle.getName());
                        existingVehicle.setPrice(updatedVehicle.getPrice());
                    });
    }
}
