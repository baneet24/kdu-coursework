package com.example.security.service;

import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    UserRepository repo = new UserRepository();

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAllUsers() {
        return repo.getAllUsers();
    }

    public Optional<User> getUserByName(String userName) {
        return repo.getUserByName(userName);
    }

    public void addUser(User user) {
        repo.addUser(user);
    }
}
