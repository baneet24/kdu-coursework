package com.example.jpahw.service;

import com.example.jpahw.model.Tenant;
import com.example.jpahw.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;

    @Autowired
    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public void addTenant(String name) {
        Tenant tenant = new Tenant();
        tenant.setName(name);

        tenantRepository.save(tenant);
    }

    public List<Tenant> getAllTenant() {
        return tenantRepository.findAll();
    }
}