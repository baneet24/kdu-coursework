package com.example.jpahw.service;

import com.example.jpahw.model.User;
import com.example.jpahw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Page<User> getAllUsersInPage(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    public void updateUserDetails(UUID userId, String username, int loggedIn, String timeZone) {
        userRepository.updateUserDetails(userId, username, loggedIn, timeZone);
    }
}