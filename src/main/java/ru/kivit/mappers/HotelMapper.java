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
        Hotel.ServiceLevel sl = Hotel.ServiceLevel.Low;
        switch (rs.getString("service_level")){
            case "Low":
                sl = Hotel.ServiceLevel.Low;
                break;
            case "Medium":
                sl = Hotel.ServiceLevel.Medium;
                break;
            case "High":
                sl = Hotel.ServiceLevel.High;
                break;
            case "Lux":
                sl = Hotel.ServiceLevel.Lux;
                break;
        }
        hotel.setService_level(sl);

        return hotel;
    }
}
