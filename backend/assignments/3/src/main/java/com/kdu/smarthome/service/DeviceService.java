package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.DeviceRequestDTO;
import com.kdu.smarthome.dto.DeviceAddRequestDTO;
import com.kdu.smarthome.exception.HouseNotFoundException;
import com.kdu.smarthome.exception.RoomNotFoundException;
import com.kdu.smarthome.model.Device;
import com.kdu.smarthome.model.House;
import com.kdu.smarthome.model.Room;
import com.kdu.smarthome.repository.DeviceRepository;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceService {


    private final DeviceRepository deviceRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, RoomRepository roomRepository) {
        this.deviceRepository = deviceRepository;
        this.roomRepository = roomRepository;
    }

    public String registerDevice(DeviceRequestDTO deviceRequest) {
        Device device = new Device();
        device.setKickstonId(deviceRequest.getKickstonId());
        device.setDeviceUsername(deviceRequest.getDeviceUsername());
        device.setDevicePassword(deviceRequest.getDevicePassword());

        deviceRepository.save(device);

        return "Device registered successfully";
    }


    public String addDeviceToHouse(DeviceAddRequestDTO deviceAddRequest) {

        Room room = roomRepository.findById(Long.valueOf(deviceAddRequest.getRoomId()))
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

        if (deviceRepository.existsByKickstonId(deviceAddRequest.getKickstonId())) {
            return "Device with kickstonId " + deviceAddRequest.getKickstonId() + " already exists";
        }

        Device device = new Device();
        device.setKickstonId(deviceAddRequest.getKickstonId());
        device.setRoom(room);


        deviceRepository.save(device);

        room.getDevices().add(device);
        roomRepository.save(room);

        return "Device added to house successfully";
    }
}
