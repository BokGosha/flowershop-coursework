package com.example.flowershop.services;

import com.example.flowershop.entities.Order;
import com.example.flowershop.entities.OrderItem;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersByUserId(Long userId);
    void saveOrder(Order order);
    void saveOrderItem(OrderItem orderItem);
}
