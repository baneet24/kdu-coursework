package com.kdu.smarthome.repository;

import com.kdu.smarthome.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    boolean existsByKickstonId(String kickstonId);
}
