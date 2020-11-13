package ru.kivit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kivit.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
