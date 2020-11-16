package ru.kivit.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.kivit.mappers.HotelMapper;
import ru.kivit.models.Hotel;

import java.util.List;

public class HotelDAO implements BaseDAO<Hotel> {

    private final JdbcTemplate jdbcTemplate;

    public HotelDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Hotel> findAll() {

        return jdbcTemplate.query("SELECT * FROM hotels", new HotelMapper());
    }

    @Override
    public Hotel findById(Long id) {

        Object[] hotel = new Object[] {id};

        return jdbcTemplate.queryForObject("SELECT * FROM hotels WHERE hotel_id = ?", hotel, new HotelMapper());

    }

    @Override
    public Hotel save(Hotel newHotel) {

        String name = newHotel.getName();
        String service_level = newHotel.getService_level().name();

        Object[] params = new Object[] {name, service_level};

        jdbcTemplate.update("INSERT INTO hotels (name, service_level) VALUES (?, ?)", params);

        return newHotel;
    }

    @Override
    public void update(Hotel updateHotel, Long id) {

        String name = updateHotel.getName();
        String service_level = updateHotel.getService_level().name();

        Object[] params = new Object[] {name, service_level, id};


        jdbcTemplate.update("UPDATE hotels SET name = ?, service_level = ? WHERE hotel_id = ?", params);

    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM hotels WHERE hotel_id = ?", id);
    }
}
