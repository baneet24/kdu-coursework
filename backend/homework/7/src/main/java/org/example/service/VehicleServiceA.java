package org.example.service;

import org.example.entities.Vehicle;
import org.example.utility.Constants;
import org.example.utility.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("vehicleServiceA")
public class VehicleServiceA {
    private TyreService tyreService;

    private SpeakerService speakerService;

    private InventoryStore inventoryStore;

    public TyreService getTyreService() {
        return tyreService;
    }

    public SpeakerService getSpeakerService() {
        return speakerService;
    }

    public InventoryStore getInventoryStore() {
        return inventoryStore;
    }

    public void setSpeakerService(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }
    public void setTyreService(TyreService tyreService) {
        this.tyreService = tyreService;
    }
    public void setInventoryStore(InventoryStore inventoryStore) {
        this.inventoryStore = inventoryStore;
    }

    @Autowired
    public VehicleServiceA(@Qualifier("mrf") TyreService tyreService,
                                  @Qualifier("sony") SpeakerService speakerService,
                                  InventoryStore inventoryStore) {
        this.tyreService = tyreService;
        this.speakerService = speakerService;
        this.inventoryStore = inventoryStore;
    }


    /**
     * adds vehicles to inventory store and add price according to speaker, location and tyres
     */
    public void manufactureVehicle() {
        double vehiclePrice = (Constants.VEHICLE_PRICE + Constants.SONY_SPEAKER_PRICE + Constants.MRF_TYRE_PRICE)*Constants.FACTOR_ONE;
        Vehicle vehicle = new Vehicle(speakerService.speaker1(), tyreService.tyre1(), vehiclePrice);
        inventoryStore.addVehicle(vehicle);
    }

    /**
     * prints the prices of vehicles
     */
    public void printInventory() {
        LoggerService.logInfo("Factory 1 Inventory:");
        inventoryStore.getAllVehicles().forEach(vehicle -> LoggerService.logInfo("Price: " + vehicle.getPrice()));
    }
}

