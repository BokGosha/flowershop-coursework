package edu.bokgosha.flowershop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String state;
    private String orderDate;
    private int totalCost;
    private String paymentChoose;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @OneToMany
    private List<OrderItem> orderItems;

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }
}
