package com.example.assessment2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;


@Data
@Entity
@Table(name="orders")
public class Orders extends BaseEntity{
    @Id
    private UUID id;
    private String shippingAddress;
    private int amount;
    private LocalDate orderDate;
    @ManyToOne
    private Users users;
   @ManyToOne
    private UserCart userCart;
    boolean isPlaced;
}
