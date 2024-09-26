package com.kdu.smarthome.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String firstName;

    private String lastName;

    @Id
    private String username;

    private String password;

    private String emailId;

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;
}
