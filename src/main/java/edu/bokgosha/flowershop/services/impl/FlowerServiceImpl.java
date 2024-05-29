package edu.bokgosha.flowershop.services.impl;

import edu.bokgosha.flowershop.services.FlowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import edu.bokgosha.flowershop.entities.Flower;
import edu.bokgosha.flowershop.repositories.FlowerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlowerServiceImpl implements FlowerService {
    private final FlowerRepository flowerRepository;

    public List<Flower> getAllFlowers() {
        return flowerRepository.findAll();
    }
}
