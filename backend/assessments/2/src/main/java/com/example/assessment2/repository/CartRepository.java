package com.example.assessment2.repository;

import com.example.assessment2.model.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public interface CartRepository extends JpaRepository<UserCart, UUID> {
}
