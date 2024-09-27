package com.example.jpahw.controller;

package com.example.jpahw.controller;

import com.example.jpahw.model.ShiftType;
import com.example.jpahw.service.ShiftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

public class ShiftTypeController {
    private final ShiftTypeService shiftTypeService;

    @Autowired
    public ShiftTypeController(ShiftTypeService shiftTypeService) {
        this.shiftTypeService = shiftTypeService;
    }

    @PostMapping("/shiftType")
    public ResponseEntity<String> saveShiftType(@RequestBody ShiftType shiftType) {
        shiftTypeService.saveShiftType(shiftType);
        return new ResponseEntity<>("ShiftType saved successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{shiftTypeId}")
    public ResponseEntity<ShiftType> getShiftTypeById(@PathVariable UUID shiftTypeId) {
        ShiftType retrievedShiftType = shiftTypeService.getShiftTypeById(shiftTypeId);
        if (retrievedShiftType != null) {
            return new ResponseEntity<>(retrievedShiftType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allShiftTypes")
    public ResponseEntity<List<ShiftType>> getAllShiftTypes() {
        List<ShiftType> allShiftTypes = shiftTypeService.getAllShiftTypes();
        return new ResponseEntity<>(allShiftTypes, HttpStatus.OK);
    }

}
