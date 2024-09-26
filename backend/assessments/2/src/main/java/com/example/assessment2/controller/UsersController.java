package com.example.assessment2.controller;

import com.example.assessment2.dao.PersonDAO;
import com.example.assessment2.exception.custom.MyCustomException;
import com.example.assessment2.model.Users;
import com.example.assessment2.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.UUID;

@RestController
public class UsersController {

private final UsersService usersService;

private final PasswordEncoder passwordEncoder;

private final PersonDAO personDAO;
    @Autowired
    public UsersController(UsersService usersService, PasswordEncoder passwordEncoder, PersonDAO personDAO){
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
        this.personDAO = personDAO;
    }
    private final Logger logger = LoggerFactory.getLogger(UsersController.class);
    @PostMapping("/user")
    public ResponseEntity<String> addUser(@RequestBody Users user){
        logger.info("Adding a new user in database");
        usersService.saveUser(user);
        personDAO.addPerson(new Users(user.getId(), user.getName(),user.getEmail(), passwordEncoder.encode(user.getPassword()),user.getAddresses(), "ROLE_USER"));
        return ResponseEntity.ok("User Added successfully!");
    }

    @GetMapping("/user/{id}")
    public Users getUser(@PathVariable UUID id){
        Users user = null;
        try{
            user = usersService.getUserById(id);
        }catch(IndexOutOfBoundsException ex){
            throw new MyCustomException("No data of id - " + id + "in storage");
        }
        return user;
    }


    @GetMapping("/search/user")
    public Users searchUser(@RequestParam String name){
        logger.info("searching user");
        Users temp = null;
        for (Users user: usersService.getAllUsers()){
            if (user.getName().equals(name)){
                temp = user;
            }
        }
        return temp;
    }
}
