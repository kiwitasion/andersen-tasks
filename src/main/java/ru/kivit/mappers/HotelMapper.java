package ru.kivit.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kivit.models.Hotel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HotelMapper implements RowMapper<Hotel> {
    @Override
    public Hotel mapRow(ResultSet rs, int i) throws SQLException {
        final Hotel hotel = new Hotel();

        hotel.setHotel_id(rs.getLong("hotel_id"));
        hotel.setName(rs.getString("name"));
        hotel.setService_level(Hotel.ServiceLevel.valueOf(rs.getString("service_level")));

        return hotel;
    }
}
