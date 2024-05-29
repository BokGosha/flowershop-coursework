package com.example.flowershop.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.flowershop.security.CustomUserDetails;
import com.example.flowershop.services.UserService;
import com.example.flowershop.entities.User;
import com.example.flowershop.repositories.RoleRepository;
import com.example.flowershop.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createUser(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of(roleRepository.findRoleById(0)));
            user.setAccountNonLocked(true);

            userRepository.save(user);
        }
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        List<User> users = new ArrayList<>();

        for (User user : allUsers) {
            if (!user.getRoles().getFirst().getRole().equals("ROLE_ADMIN")) {
                users.add(user);
            }
        }

        return users;
    }

    @Override
    public User getUserById(Long id){
        return userRepository.findUserById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByUsername(username);

        return user.map(CustomUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
