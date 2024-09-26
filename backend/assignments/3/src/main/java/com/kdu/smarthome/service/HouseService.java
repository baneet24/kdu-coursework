package com.kdu.smarthome.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kdu.smarthome.dto.HouseRequestDTO;
import com.kdu.smarthome.dto.HouseResponseDTO;
import com.kdu.smarthome.dto.RoomRequestDTO;
import com.kdu.smarthome.dto.RoomResponseDTO;
import com.kdu.smarthome.exception.HouseNotFoundException;
import com.kdu.smarthome.model.Device;
import com.kdu.smarthome.model.House;
import com.kdu.smarthome.model.Room;
import com.kdu.smarthome.repository.HouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class HouseService {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public HouseResponseDTO registerHouse(HouseRequestDTO houseRequestDTO, String adminUsername) {
        House newHouse = new House();
        newHouse.setHouseName(houseRequestDTO.getHouseName());
        newHouse.setAddress(houseRequestDTO.getAddress());
        newHouse.setUsers(Collections.singletonList(adminUsername));

        House savedHouse = houseRepository.save(newHouse);

        return new HouseResponseDTO(String.valueOf(savedHouse.getId()));
    }

    public House findHouseById(String houseId) {
        return houseRepository.findById(Long.valueOf(houseId)).orElse(null);
    }

    public void saveHouse(House house) {
        houseRepository.save(house);
    }

    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }

    public House updateHouseAddress(Long houseId, String newAddress) {
        Optional<House> optionalHouse = houseRepository.findById(houseId);
        if (optionalHouse.isPresent()) {
            House house = optionalHouse.get();
            house.setAddress(newAddress);
            return houseRepository.save(house);
        } else {
            throw new HouseNotFoundException("House not found with id: " + houseId);
        }
    }

    public RoomResponseDTO addRoomToHouse(Long houseId, RoomRequestDTO roomRequest) {
        House house = houseRepository.findById(houseId).orElse(null);

        if (house == null) {
            return createNotFoundResponse();
        }

        Room room = new Room();
        room.setName(roomRequest.getRoomName());

        house.getRooms().add(room);
        houseRepository.save(house);

        RoomResponseDTO response = new RoomResponseDTO();
        response.setMessage("Room added successfully");
        response.setRoom(mapRoomToDto(room));
        response.setHttpStatus(HttpStatus.OK);

        return response;
    }

    private RoomResponseDTO createNotFoundResponse() {
        RoomResponseDTO response = new RoomResponseDTO();
        response.setMessage("House not found");
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        return response;
    }

    private Room mapRoomToDto(Room room) {
        Room dto = new Room();
        dto.setId(room.getId());
        dto.setName(room.getName());
        return dto;
    }

    public ResponseEntity<List<Device>> getDevicesByHouseId(Long houseId) {
        Optional<House> optionalHouse = houseRepository.findById(houseId);
        if (optionalHouse.isPresent()) {
            House house = optionalHouse.get();
            List<Device> devices = house.getDevices();
            return ResponseEntity.ok(devices);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<List<Room>> getRoomsByHouseId(Long houseId) {
        Optional<House> optionalHouse = houseRepository.findById(houseId);
        if (optionalHouse.isPresent()) {
            House house = optionalHouse.get();
            List<Room> rooms = house.getRooms();
            return ResponseEntity.ok(rooms);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    public ResponseEntity<String> getRoomsAndDevicesByHouseId(Long houseId) {
        House house = houseRepository.findById(houseId).orElse(null);

        if (house == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("House not found");
        }

        String roomsAndDevicesJson = mapRoomsAndDevicesToJson(house);

        return ResponseEntity.ok(roomsAndDevicesJson);
    }

    private String mapRoomsAndDevicesToJson(House house) {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> response = new HashMap<>();
        response.put("houseId", house.getId());
        response.put("houseName", house.getHouseName());
        response.put("address", house.getAddress());
        response.put("rooms", house.getRooms());
        response.put("devices", house.getDevices());

        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.error("cannot convert map to string");
            return "";
        }
    }
}