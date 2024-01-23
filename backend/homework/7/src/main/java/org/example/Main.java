package org.example;

import org.example.config.Config;
import org.example.entities.Vehicle;
import org.example.service.VehicleServiceA;
import org.example.service.VehicleServiceB;
import org.example.utility.LoggerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        VehicleServiceA factory1 = context.getBean("vehicleServiceA", VehicleServiceA.class);
        VehicleServiceB factory2 = context.getBean("vehicleServiceB", VehicleServiceB.class);

        factory1.manufactureVehicle();
        factory2.manufactureVehicle();

        LoggerService.logInfo("Factory 1 Inventory:");
        factory1.printInventory();

        LoggerService.logInfo("Factory 2 Inventory:");
        factory2.printInventory();

        Vehicle mostExpensiveVehicleFactory1 = factory1.getInventoryStore().findHighestPricedVehicle();
        Vehicle leastExpensiveVehicleFactory1 = factory1.getInventoryStore().findLowestPricedVehicle();
        LoggerService.logInfo(String.valueOf(mostExpensiveVehicleFactory1.getPrice()));
        LoggerService.logInfo(String.valueOf(leastExpensiveVehicleFactory1.getPrice()));

        Vehicle mostExpensiveVehicleFactory2 = factory2.getInventoryStore().findHighestPricedVehicle();
        Vehicle leastExpensiveVehicleFactory2 = factory2.getInventoryStore().findLowestPricedVehicle();
        LoggerService.logInfo(String.valueOf(mostExpensiveVehicleFactory2.getPrice()));
        LoggerService.logInfo(String.valueOf(leastExpensiveVehicleFactory2.getPrice()));
    }

}

