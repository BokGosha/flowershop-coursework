package com.example.flowershop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flowershop.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleById(long id);
}
