package com.example.assessment2.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class UserCart extends BaseEntity{
    @Id
    private UUID id;
    @OneToOne
    private Users users;
    @OneToMany
    private List<Product> product;
}

