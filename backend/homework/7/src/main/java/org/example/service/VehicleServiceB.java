package org.example.service;


import org.example.entities.Vehicle;
import org.example.utility.Constants;
import org.example.utility.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service("vehicleServiceB")
public class VehicleServiceB {
    private TyreService tyreService;
    private SpeakerService speakerService;
    private InventoryStore inventoryStore;

    @Autowired
    public VehicleServiceB(@Qualifier("bridgestone") TyreService tyreService,
                                  @Qualifier("bose") SpeakerService speakerService,
                                  InventoryStore inventoryStore) {
        this.tyreService = tyreService;
        this.speakerService = speakerService;
        this.inventoryStore = inventoryStore;
    }


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


    /**
     * adds vehicles to inventory store and add price according to speaker, location and tyres
     */
    public void manufactureVehicle() {
        double vehiclePrice = (Constants.VEHICLE_PRICE + Constants.BOSE_SPEAKER_PRICE + Constants.BRIDGESTONE_TYRE_PRICE)*Constants.FACTOR_TWO;
        Vehicle vehicle = new Vehicle(speakerService.speaker2(), tyreService.tyre2(), vehiclePrice);
        inventoryStore.addVehicle(vehicle);
    }


    /**
     * prints the prices of vehicles
     */
    public void printInventory() {
        LoggerService.logInfo("Factory 2 Inventory:");
        inventoryStore.getAllVehicles().forEach(vehicle -> LoggerService.logInfo("Price: " + vehicle.getPrice()));
    }
}

