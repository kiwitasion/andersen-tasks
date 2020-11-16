package ru.kivit.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.kivit.mappers.OrderMapper;
import ru.kivit.models.*;

import java.util.List;

public class OrderDAO implements BaseDAO<Order> {

    private final JdbcTemplate jdbcTemplate;

    public OrderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> findAll() {

        return jdbcTemplate.query("SELECT * FROM orders", new OrderMapper());
    }

    @Override
    public Order findById(Long id) {

        Object[] order = new Object[] {id};

        return jdbcTemplate.queryForObject("SELECT * FROM orders WHERE order_id = ?", order, new OrderMapper());
    }

    @Override
    public Order save(Order newOrder) {

        String number = newOrder.getNumber();
        Integer price = newOrder.getPrice();
        Long user_id = newOrder.getUser().getUser_id();
        Long tour_id = newOrder.getTour().getTour_id();

        Object[] params = new Object[] {number, price, user_id, tour_id};

        jdbcTemplate.update("INSERT INTO orders (number, price, user_id, tour_id) VALUES (?, ?, ?, ?)", params);

        return newOrder;
    }

    @Override
    public void update(Order updateOrder, Long id) {

        String number = updateOrder.getNumber();
        Integer price = updateOrder.getPrice();


        Object[] params = new Object[] {number, price, id};

        jdbcTemplate.update("UPDATE orders SET number = ?, price = ? WHERE order_id = ?", params);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM orders WHERE order_id = ?", id);
    }
}
