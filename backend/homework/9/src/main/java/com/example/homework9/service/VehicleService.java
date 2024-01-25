package com.example.homework9.service;

import com.example.homework9.Constants;
import com.example.homework9.dto.RequestDTO;
import com.example.homework9.dto.ResponseDTO;
import com.example.homework9.entity.Vehicle;
import com.example.homework9.exception.customexceptions.ResourceNotFoundException;
import com.example.homework9.logger.LoggerService;
import com.example.homework9.repository.FactoryInventory;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleService {
    FactoryInventory factory;

    @Autowired
    public VehicleService(){
        this.factory = new FactoryInventory();
    }

    public void addToInventory(RequestDTO vehicleDTO) throws BadRequestException {
        if(vehicleDTO == null){
            throw new BadRequestException(Constants.BAD_REQUEST_EXCEPTION_MESSAGE);
        }
        else if(vehicleDTO.getName() == null){
            throw new BadRequestException("name not found");
        }
        else {
            Vehicle vehicle = DTOmapper.requestDTOToVehicle(vehicleDTO);
            factory.addVehicle(vehicle);
            LoggerService.logInfo("Vehicle added successfully");
        }
    }

    public ResponseDTO getVehicleFromInventory(int id) {
        Optional<Vehicle> vehicleDTOOptional = factory.retrieveVehicle(id);

        if (vehicleDTOOptional.isPresent()) {
            LoggerService.logInfo("Vehicle found successfully");
            return DTOmapper.vehicleToVehicleResponseDTO(vehicleDTOOptional.get());
        }
        else {
            throw new ResourceNotFoundException("Vehicle with ID " + id + " not found");
        }
    }

    /**
     * @param id
     * @param updatedVehicleDTO vehicle to be updated
     */
    public void updateInInventory(int id, RequestDTO updatedVehicleDTO){
        Vehicle updatedVehicle = DTOmapper.requestDTOToVehicle(updatedVehicleDTO);
        factory.updateVehicle(id,updatedVehicle);
        LoggerService.logInfo("Vehicle updated successfully");
    }

    public void deleteFromInventory(RequestDTO vehicleDTO){
        if(vehicleDTO == null) throw new ResourceNotFoundException(Constants.VEHICLE_EXCEPTION_MESSAGE);
        Vehicle vehicle = DTOmapper.requestDTOToVehicle(vehicleDTO);
        factory.deleteVehicle(vehicle);
        LoggerService.logInfo("Vehicle deleted successfully");
    }

    /**
     *most expensive vehicle from list of all vehicles belonging to a factory
     */

    public ResponseDTO findHighestPricedVehicle() {
        Vehicle vehicle = factory.getAllVehicles().stream()
                .max((v1, v2) -> Double.compare(v1.getPrice(), v2.getPrice()))
                .orElse(null);

        if(vehicle == null){
            throw new ResourceNotFoundException(Constants.VEHICLE_EXCEPTION_MESSAGE);
        }
        LoggerService.logInfo("Highest priced vehicle fetched successfully");
        return DTOmapper.vehicleToVehicleResponseDTO(vehicle);
    }

    /**
     * @return least expensive vehicle from list of all vehicles belonging to a factory
     */
    public ResponseDTO findLowestPricedVehicle() {
        Vehicle vehicle = factory.getAllVehicles().stream()
                .min((v1, v2) -> Double.compare(v1.getPrice(), v2.getPrice()))
                .orElse(null);

        if(vehicle == null){
            throw new ResourceNotFoundException(Constants.VEHICLE_EXCEPTION_MESSAGE);
        }
        LoggerService.logInfo("Lowest priced vehicle fetched successfully");
        return DTOmapper.vehicleToVehicleResponseDTO(vehicle);
    }

}
