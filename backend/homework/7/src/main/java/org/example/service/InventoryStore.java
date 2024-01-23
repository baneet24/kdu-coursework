package org.example.service;

import org.springframework.stereotype.Component;
import org.example.entities.Vehicle;
import org.springframework.context.annotation.Scope;


import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class InventoryStore {

    private final List<Vehicle> vehicleList = new ArrayList<>();

    public void addVehicle(Vehicle vehicle) {
        vehicleList.add(vehicle);
    }

    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehicleList);
    }

    /**
     * @return most expensive vehicle from list of all vehicles belonging to a factory
     */
    public Vehicle findHighestPricedVehicle() {
        return vehicleList.stream()
                .max((v1, v2) -> Double.compare(v1.getPrice(), v2.getPrice()))
                .orElse(null);
    }

    /**
     * @return least expensive vehicle from list of all vehicles belonging to a factory
     */
    public Vehicle findLowestPricedVehicle() {
        return vehicleList.stream()
                .min((v1, v2) -> Double.compare(v1.getPrice(), v2.getPrice()))
                .orElse(null);
    }
}
