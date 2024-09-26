package com.example.assessment2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping ("/person/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>("success login", HttpStatus.CREATED);
    }
}
