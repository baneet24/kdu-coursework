package com.kdu.smarthome.controller;


import com.kdu.smarthome.dto.*;
import com.kdu.smarthome.model.House;
import com.kdu.smarthome.model.Person;
import com.kdu.smarthome.service.HouseService;
import com.kdu.smarthome.service.PersonService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.kdu.smarthome.exception.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/house")
public class HouseController {
    private final HouseService houseService;
    private final PersonService userService;

    @Autowired
    public HouseController(HouseService houseService, PersonService userService) {
        this.houseService = houseService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<HouseResponseDTO> registerHouse(@RequestBody HouseRequestDTO houseRequestDTO, HttpServletRequest request) {
        if(request.getHeader("Authorization")==null){
            throw new AccessDeniedException("Unauthorized access");
        }
        String authToken = request.getHeader("Authorization").substring(7);
        HouseResponseDTO houseResponseDTO = houseService.registerHouse(houseRequestDTO, authToken);
        return new ResponseEntity<>(houseResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/{houseId}/add-user")
    public ResponseEntity<Object> addUserToHouse(
            @PathVariable String houseId,
            @RequestBody UserRequestDTO userRequest) {

        House house = houseService.findHouseById(houseId);
        if (house == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("House not found");
        }

        Person newUser = userService.createUser(userRequest.getUsername());
        house.getUsers().add(String.valueOf(newUser));

        houseService.saveHouse(house);

        UserResponseDTO userResponse = new UserResponseDTO("User added successfully", newUser);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllHouses() {
        List<House> houses = houseService.getAllHouses();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully retrieved the list of houses");
        response.put("houses", houses);
        response.put("httpStatus", HttpStatus.OK);

        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<House> updateHouseAddress(@RequestParam Long houseId,
                                                    @RequestParam String newAddress) {
        House updatedHouse = houseService.updateHouseAddress(houseId, newAddress);
        return ResponseEntity.ok(updatedHouse);
    }


    @PostMapping("/{houseId}/add-room")
    public ResponseEntity<RoomResponseDTO> addRoomToHouse(@PathVariable Long houseId, @RequestBody RoomRequestDTO roomRequest) {
        RoomResponseDTO response = houseService.addRoomToHouse(houseId, roomRequest);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @GetMapping("/{houseId}/rooms-and-devices")
    public ResponseEntity<String> getRoomsAndDevicesByHouseId(@PathVariable Long houseId) {
        return houseService.getRoomsAndDevicesByHouseId(houseId);
    }

}