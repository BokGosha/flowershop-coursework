package com.example.flowershop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flowershop.entities.Flower;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {
}
