package ru.kivit.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kivit.models.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int i) throws SQLException {
        final Order order = new Order();

        order.setOrder_id(rs.getLong("order_id"));
        order.setNumber(rs.getString("number"));
        order.setPrice(rs.getInt("price"));
        User user = new User();
        user.setUser_id(rs.getLong("user_id"));
        order.setUser(user);
        Tour tour = new Tour();
        tour.setTour_id(rs.getLong("tour_id"));
        order.setTour(tour);

        return order;
    }
}
