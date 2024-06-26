package edu.bokgosha.flowershop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "flower_id")
    private Flower flower;
    private int amount;
    @ManyToOne
    private Order order;
}
