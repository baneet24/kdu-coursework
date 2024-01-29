package com.example.security.controller;

import com.example.security.exceptions.customexceptions.EmptyListException;
import com.example.security.model.User;
import com.example.security.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/Allusers")
    public ResponseEntity<List<User>> getAllUsers() throws EmptyListException {
        List<User> users = userService.getAllUsers();
        logger.info("All users retrieved");
        if(users.isEmpty()){
            throw new EmptyListException("List is empty");
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{userName}")
    public ResponseEntity<User> getUserByName(@PathVariable String userName) {
        Optional<User> user = userService.getUserByName(userName);
        logger.info("User with given name retrieved");
        return user.map(ResponseEntity::ok)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with the specified name: " + userName));

    }

    @GetMapping("/find/user")
    public ResponseEntity<User> searchUser(@RequestParam String userName) {
        Optional<User> user = userService.getUserByName(userName);
        logger.info("Searched user successfully");
        return user.map(ResponseEntity::ok).orElseThrow(() -> new UsernameNotFoundException("User not found with the specified name: " + userName));
    }

    @PostMapping("/admin")
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        userService.addUser(user);
        logger.info("User added");
        return ResponseEntity.ok().build();
    }

}
