package com.example.flowershop.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.flowershop.entities.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void createUser(User user);
    void saveUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
}
