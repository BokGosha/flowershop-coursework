package edu.bokgosha.flowershop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "flowers")
@Getter
@Setter
public class Flower {
    @Id
    private int id;
    private String name;
    private String flowerPrice;
    @Column(length = 1000)
    private String description;
    @OneToMany(mappedBy = "flower")
    private List<OrderItem> orderItem;
}
