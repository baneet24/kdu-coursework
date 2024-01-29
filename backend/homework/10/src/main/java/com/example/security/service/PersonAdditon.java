package com.example.security.service;

import com.example.security.dao.PersonDAO;
import com.example.security.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonAdditon implements CommandLineRunner {

    @Autowired
    PersonDAO personDAO;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        personDAO.addPerson(new Person("Admin", "IamAdmin", passwordEncoder.encode("MeAdmin@123"), "ROLE_ADMIN"));
        personDAO.addPerson(new Person("Kate", "kate", passwordEncoder.encode("password123"), "ROLE_BASIC"));
    }
}

