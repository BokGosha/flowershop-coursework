package com.example.flowershop.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.flowershop.services.OrderService;
import com.example.flowershop.entities.Order;
import com.example.flowershop.entities.OrderItem;
import com.example.flowershop.repositories.OrderItemRepository;
import com.example.flowershop.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findAllByUserId(userId);
    }

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public void saveOrderItem(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }
}
