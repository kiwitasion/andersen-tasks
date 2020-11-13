package ru.kivit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kivit.models.Order;
import ru.kivit.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order findById(Long id){
        return repository.getOne(id);
    }

    public List<Order> findAll(){

        return repository.findAll();
    }

    public Order save(Order order){
        return repository.save(order);
    }

    public Order update(Order newOrder, Long id){
        Order oldOrder = repository.findById(id).orElse(null);
        oldOrder.setNumber(newOrder.getNumber());
        oldOrder.setPrice(newOrder.getPrice());
        return  repository.save(oldOrder);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
}
