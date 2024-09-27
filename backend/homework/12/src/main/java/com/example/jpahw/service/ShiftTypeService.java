package com.example.jpahw.service;

import com.example.jpahw.model.ShiftType;
import com.example.jpahw.repository.ShiftTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ShiftTypeService {
    private final ShiftTypeRepository shiftTypeRepository;

    @Autowired
    public ShiftTypeService(ShiftTypeRepository shiftTypeRepository) {
        this.shiftTypeRepository = shiftTypeRepository;
    }

    /**
     * @param shiftType add shift type to repo
     */
    public void saveShiftType(ShiftType shiftType) {
        shiftTypeRepository.save(shiftType);
    }

    /**
     * @param shiftTypeId
     * @return shift type that matches the ID
     */
    public ShiftType getShiftTypeById(UUID shiftTypeId) {
        return shiftTypeRepository.findById(shiftTypeId).orElse(null);
    }

    public List<ShiftType> getAllShiftTypes() {
        return shiftTypeRepository.findAll();
    }
}