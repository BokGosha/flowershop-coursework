package edu.bokgosha.flowershop.services.impl;

import edu.bokgosha.flowershop.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import edu.bokgosha.flowershop.entities.Order;
import edu.bokgosha.flowershop.entities.OrderItem;
import edu.bokgosha.flowershop.repositories.OrderItemRepository;
import edu.bokgosha.flowershop.repositories.OrderRepository;

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
