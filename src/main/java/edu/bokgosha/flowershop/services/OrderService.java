package edu.bokgosha.flowershop.services;

import edu.bokgosha.flowershop.entities.Order;
import edu.bokgosha.flowershop.entities.OrderItem;

import java.util.List;

public interface OrderService {
    List<Order> getOrdersByUserId(Long userId);
    void saveOrder(Order order);
    void saveOrderItem(OrderItem orderItem);
}
