package com.example.jpahw.controller;

import com.example.jpahw.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.jpahw.service.TenantService;

import java.util.List;

@RestController
public class TenantController {
    private final TenantService tenantService;

    @Autowired
    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping("/tenant")
    public ResponseEntity<String> createTenant(@RequestBody String name) {
        tenantService.addTenant(name);
        return new ResponseEntity<>("Tenant created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/allTenants")
    public ResponseEntity<List<Tenant>> getAllTenants() {
        List<Tenant> allTenants = tenantService.getAllTenant();
        return new ResponseEntity<>(allTenants, HttpStatus.OK);
    }
}