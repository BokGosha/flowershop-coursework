package edu.bokgosha.flowershop.repositories;

import edu.bokgosha.flowershop.entities.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Long> {
}
