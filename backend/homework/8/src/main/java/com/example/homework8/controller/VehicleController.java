package com.example.homework8.controller;

import com.example.homework8.dto.VehicleDTO;
import com.example.homework8.entity.Vehicle;
import com.example.homework8.service.DTOMapper;
import com.example.homework8.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {

    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/addvehicle")
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle vehicle){
        vehicleService.addToInventory(DTOMapper.convertToDTO(vehicle));
        return ResponseEntity.ok("Vehicle added successfully");
    }

    @GetMapping("/getvehiclewithID/{id}")
    public Vehicle getVehicle(@PathVariable int id){
        VehicleDTO vehicleDTO = vehicleService.getVehicleFromInventory(id);
        return DTOMapper.convertToEntity(vehicleDTO);
    }

    @PutMapping("/updateVehiclewithID/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable int id, @RequestBody Vehicle vehicle){
        vehicleService.updateInInventory(id, DTOMapper.convertToDTO(vehicle));
        return ResponseEntity.ok("Vehicle updated successfully");
    }

    @DeleteMapping("/deleteVehicle")
    public ResponseEntity<String> deleteVehicle(@RequestBody Vehicle vehicle){
        vehicleService.deleteFromInventory(DTOMapper.convertToDTO(vehicle));
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @GetMapping("/findprice/{type}")
    public String showMaxOrMinPrice(@PathVariable String type)
    {
        if(type.equals("Highest"))
            return "highest = " + vehicleService.findHighestPricedVehicle();
        else
            return "lowest = " + vehicleService.findLowestPricedVehicle();
    }


}