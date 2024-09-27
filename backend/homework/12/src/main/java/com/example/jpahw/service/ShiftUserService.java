package com.example.jpahw.service;

import com.example.jpahw.exceptions.customexceptions.NotFoundException;
import com.example.jpahw.model.ShiftUser;
import com.example.jpahw.repository.ShiftUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
public class ShiftUserService {
    private final ShiftUserRepository shiftUserRepository;

    @Autowired
    public ShiftUserService(ShiftUserRepository shiftUserRepository) {
        this.shiftUserRepository = shiftUserRepository;
    }

    public void saveShiftUser(ShiftUser shiftUser) {
        shiftUserRepository.save(shiftUser);
    }

    public ShiftUser getShiftUserById(UUID shiftUserId) {
        return shiftUserRepository.findById(shiftUserId).orElse(null);
    }

    public List<ShiftUser> getAllShiftUsers() {
        return shiftUserRepository.findAll();
    }

    public void deleteShiftUser(UUID shiftUserId) throws NotFoundException {
        List<ShiftUser> shiftUsers = shiftUserRepository.findByShiftEndTimeAndId(LocalTime.of(23, 0), shiftUserId);

        if (!shiftUsers.isEmpty()) {
            shiftUserRepository.deleteById(shiftUserId);
        } else {
            throw new NotFoundException("Shift User not found");
        }
    }
}