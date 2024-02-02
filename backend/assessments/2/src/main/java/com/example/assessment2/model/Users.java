package com.example.assessment2.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Table(name="users")
public class Users extends BaseEntity{
    @Id
    private int id;
    private String name;
    private String email;
    private String password;
    @OneToMany
    List<Address> addresses;
    private String role;
}
