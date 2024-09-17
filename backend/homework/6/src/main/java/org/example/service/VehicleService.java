package org.example.service;


import jakarta.annotation.PostConstruct;
import org.example.entities.Vehicle;
import org.example.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.example.exception.VehicleNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleService {

    private final List<Vehicle> vehicleList = new ArrayList<>();
    @Autowired
    private TyreService tyreService;

    @Autowired
    private SpeakerService speakerService;

    /**
     * create vehicles with different types of speakers and tyres
     * and add it to list
     */
    @PostConstruct
    public void addVehicles(){
        Vehicle hondaCity = new Vehicle(speakerService.speaker1(),tyreService.tyre1(), Constants.VEHICLE_PRICE + speakerService.speaker1().getPrice() + tyreService.tyre1().getPrice()*Constants.NUMBER_OF_TYRES);
        vehicleList.add(hondaCity);
        Vehicle verna= new Vehicle(speakerService.speaker1(),tyreService.tyre2(), Constants.VEHICLE_PRICE + speakerService.speaker1().getPrice() + tyreService.tyre2().getPrice()*Constants.NUMBER_OF_TYRES);
        vehicleList.add(verna);
        Vehicle dzire = new Vehicle(speakerService.speaker2(),tyreService.tyre1(), Constants.VEHICLE_PRICE + speakerService.speaker2().getPrice() + tyreService.tyre1().getPrice()*Constants.NUMBER_OF_TYRES);
        vehicleList.add(dzire);
        Vehicle rover= new Vehicle(speakerService.speaker2(),tyreService.tyre2(), Constants.VEHICLE_PRICE + speakerService.speaker2().getPrice() + tyreService.tyre2().getPrice()*Constants.NUMBER_OF_TYRES);
        vehicleList.add(rover);
    }

    /**
     * compares prices of vehicles from list of vehicles
     * @return vehicle with highest price
     */
    public Vehicle findMostExpensiveVehicle() {
        Vehicle mostExpensiveVehicle =  vehicleList.stream()
                .reduce((v1, v2) -> v1.getPrice() > v2.getPrice() ? v1 : v2)
                .orElse(null);
        if(mostExpensiveVehicle != null) return mostExpensiveVehicle;
        else throw new VehicleNotFoundException("Vehicle not found");
    }

}
