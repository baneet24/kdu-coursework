package com.example.jpahw.service;

import com.example.jpahw.model.Shift;
import com.example.jpahw.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ShiftService {
    private final ShiftRepository shiftRepository;

    @Autowired
    public ShiftService(ShiftRepository shiftRepository) {
        this.shiftRepository = shiftRepository;
    }

    public void saveShift(Shift shift) {
        shiftRepository.save(shift);
    }

    public Shift getShiftById(UUID shiftId) {
        return shiftRepository.findById(shiftId).orElse(null);
    }

    public List<Shift> getAllShifts() {
        return shiftRepository.findAll();
    }


    /**
     * @param startDate
     * @param endDate
     * @return s top 3 shifts whose start_date = “startDate” and end_date =
     * “endDate”
     */
    public List<Shift> findTop3ShiftsByDateRange(LocalDate startDate, LocalDate endDate) {
        return shiftRepository.findTop3ShiftsByDateRange(startDate, endDate);
    }
}