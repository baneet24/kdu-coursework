package org.example;

import org.example.config.Config;
import org.example.service.VehicleService;
import org.example.utility.LoggerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        VehicleService vehicleservice=context.getBean(VehicleService.class);
        LoggerService.logInfo("Most Expensive Vehicle: " + vehicleservice.findMostExpensiveVehicle().getPrice());
    }
}