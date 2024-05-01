package com.example.flowershop.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.flowershop.services.FlowerService;
import com.example.flowershop.entities.Flower;
import com.example.flowershop.repositories.FlowerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlowerServiceImpl implements FlowerService {
    private final FlowerRepository flowerRepository;

    public List<Flower> getAllFlowers() {
        return flowerRepository.findAll();
    }
}
