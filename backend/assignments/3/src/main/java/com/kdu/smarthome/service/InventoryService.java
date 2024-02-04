package com.kdu.smarthome.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdu.smarthome.dto.InventoryRequestDTO;
import com.kdu.smarthome.dto.InventoryResponseDTO;
import com.kdu.smarthome.exception.JsonMapperException;
import com.kdu.smarthome.model.DeviceInventory;
import com.kdu.smarthome.repository.InventoryRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryResponseDTO addItemToInventory(InventoryRequestDTO request) {

        DeviceInventory item = new DeviceInventory();
        item.setKickstonId(request.getKickstonId());
        item.setDeviceUsername(request.getDeviceUsername());
        item.setDevicePassword(request.getDevicePassword());
        item.setManufacturerDateTime(request.getManufacturerDateTime());
        item.setManufacturerFactoryPlace(request.getManufacturerFactoryPlace());
        item.setAvailable(true);

        inventoryRepository.save(item);
        return new InventoryResponseDTO("Item added successfully", "Additional information", HttpStatus.OK);
    }

    public String getInventory() throws JsonMapperException {
        List<DeviceInventory> inventoryItems = inventoryRepository.findAll();

        // Convert the list to JSON format
        ObjectMapper objectMapper = new ObjectMapper();
        String inventoryJson = "[]"; // Default empty array
        try {
            inventoryJson = objectMapper.writeValueAsString(inventoryItems);
        } catch (JsonProcessingException e) {
           throw new JsonMapperException("cannot map values");
        }

        return inventoryJson;
    }
}
