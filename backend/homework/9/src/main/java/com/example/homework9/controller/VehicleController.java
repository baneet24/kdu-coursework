package com.example.homework9.controller;


import com.example.homework9.dto.RequestDTO;
import com.example.homework9.dto.ResponseDTO;
import com.example.homework9.service.VehicleService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VehicleController {
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("/newvehicle")
    public ResponseEntity<String> addVehicle(@RequestBody RequestDTO vehicleDTO) throws BadRequestException {
        vehicleService.addToInventory(vehicleDTO);
        return ResponseEntity.ok("Vehicle added successfully");
    }

    @GetMapping("/vehicle/{id}")
    public ResponseDTO getVehicle(@PathVariable int id){
        return vehicleService.getVehicleFromInventory(id);
    }

    @PutMapping("/updatedvehicle/{id}")
    public ResponseEntity<String> updateVehicle(@PathVariable int id, @RequestBody RequestDTO vehicleDTO){
        vehicleService.updateInInventory(id, vehicleDTO);
        return ResponseEntity.ok("Vehicle updated successfully");
    }

    @DeleteMapping("/deleted")
    public ResponseEntity<String> deleteVehicle(@RequestBody RequestDTO vehicleDTO){
        vehicleService.deleteFromInventory(vehicleDTO);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @GetMapping("/factory1/showprice/{type}")
    public String showMaxOrMinPrice(@PathVariable String type)
    {
        if(type.equals("Highest"))
            return "highest = " + vehicleService.findHighestPricedVehicle();
        else
            return "lowest = " + vehicleService.findLowestPricedVehicle();
    }

}
