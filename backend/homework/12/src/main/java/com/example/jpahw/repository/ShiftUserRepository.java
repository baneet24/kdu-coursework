package com.example.jpahw.repository;

import com.example.jpahw.model.ShiftUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftUserRepository extends JpaRepository<ShiftUser, UUID> {
    List<ShiftUser> findByShiftEndTimeAndId(LocalTime endTime, UUID shiftUserId);
}