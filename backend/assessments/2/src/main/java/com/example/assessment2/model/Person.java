package com.example.assessment2.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person extends BaseEntity{
    private String name;

    private String username;

    private String password;

    private String role;
}
