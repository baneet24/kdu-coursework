package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.InventoryRequestDTO;
import com.kdu.smarthome.dto.InventoryResponseDTO;
import com.kdu.smarthome.exception.JsonMapperException;
import com.kdu.smarthome.service.InventoryService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.jar.JarException;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<InventoryResponseDTO> addItemToInventory(@RequestBody InventoryRequestDTO request) {
        InventoryResponseDTO response = inventoryService.addItemToInventory(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> getInventoryItems() throws JsonMapperException {
        String inventory = inventoryService.getInventory();
        return new ResponseEntity<>(inventory, HttpStatus.OK);
    }
}

