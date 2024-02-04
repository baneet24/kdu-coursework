package com.kdu.smarthome.controller;

import com.kdu.smarthome.dto.DeviceAddRequestDTO;
import com.kdu.smarthome.dto.DeviceRequestDTO;
import com.kdu.smarthome.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/device")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerDevice(@RequestBody DeviceRequestDTO deviceRequest) {
        String message = deviceService.registerDevice(deviceRequest);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addDeviceToHouse(@RequestBody DeviceAddRequestDTO deviceAddRequest) {
        String message = deviceService.addDeviceToHouse(deviceAddRequest);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
