package com.example.assessment2.repository;

import com.example.assessment2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(value = "UPDATE product SET username = :name, quantity = :quantity, price = :price WHERE id = :userId", nativeQuery = true)
    void updateUserDetails(@Param("userId") UUID userId, @Param("name") String name, @Param("quantity") int quantity, @Param("price") double price);
}
