package edu.bokgosha.flowershop.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import edu.bokgosha.flowershop.entities.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);
    void saveUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
}
