package com.example.assessment2.service;

import com.example.assessment2.model.Users;
import com.example.assessment2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {

    private final UsersRepository userRepository;


    @Autowired
    public UsersService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(Users user) {
        userRepository.save(user);
    }

    public Users getUserById(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }


}