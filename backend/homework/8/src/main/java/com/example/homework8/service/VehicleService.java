package com.example.homework8.service;

import com.example.homework8.dto.VehicleDTO;
import com.example.homework8.repository.FactoryInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VehicleService {

    FactoryInventory factory;

    @Autowired
    public VehicleService(){
        this.factory = new FactoryInventory();
    }

    public void addToInventory(VehicleDTO vehicle){
        factory.addVehicle(vehicle);
    }
    public VehicleDTO getVehicleFromInventory(int id) {
        Optional<VehicleDTO> vehicleDTOOptional = factory.retrieveVehicle(id);

        if (vehicleDTOOptional.isPresent()) {
            return vehicleDTOOptional.get();
        }
        else {
            throw new NoSuchElementException("Vehicle with ID " + id + " not found");
        }
    }

    public void updateInInventory(int id, VehicleDTO updatedVehicle){
        factory.updateVehicle(id,updatedVehicle);
    }

    public void deleteFromInventory(VehicleDTO vehicle){
        factory.deleteVehicle(vehicle);
    }

    public VehicleDTO findHighestPricedVehicle() {
        return factory.getAllVehicles().stream()
                .max((v1, v2) -> Double.compare(v1.getPrice(), v2.getPrice()))
                .orElse(null);
    }

    /**
     * @return least expensive vehicle from list of all vehicles belonging to a factory
     */
    public VehicleDTO findLowestPricedVehicle() {
        return factory.getAllVehicles().stream()
                .min((v1, v2) -> Double.compare(v1.getPrice(), v2.getPrice()))
                .orElse(null);
    }
}

