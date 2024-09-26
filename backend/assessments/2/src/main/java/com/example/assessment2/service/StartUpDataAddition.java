package com.example.assessment2.service;

import com.example.assessment2.dao.PersonDAO;
import com.example.assessment2.model.Address;

import com.example.assessment2.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StartUpDataAddition implements CommandLineRunner {

    private final PersonDAO personDAO;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StartUpDataAddition(PersonDAO personDAO, PasswordEncoder passwordEncoder) {
        this.personDAO = personDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Address> adminAddress = new ArrayList<>();
        personDAO.addPerson(new Users(11, "baneet", "baneet@abc.com", passwordEncoder.encode("Testing123"), adminAddress, "ROLE_ADMIN"));
    }
}
