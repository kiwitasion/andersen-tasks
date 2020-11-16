package ru.kivit.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.kivit.mappers.TourMapper;
import ru.kivit.models.Country;
import ru.kivit.models.Hotel;
import ru.kivit.models.Tour;

import java.util.List;

public class TourDAO implements BaseDAO<Tour> {

    private final JdbcTemplate jdbcTemplate;

    public TourDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Tour> findAll() {

        return jdbcTemplate.query("SELECT * FROM tours", new TourMapper());
    }

    @Override
    public Tour findById(Long id) {

        Object[] tour = new Object[] {id};

        return jdbcTemplate.queryForObject("SELECT * FROM tours WHERE tour_id = ?", tour, new TourMapper());

    }

    @Override
    public Tour save(Tour newTour) {

        String name = newTour.getName();
        Long hotel_id = newTour.getHotel().getHotel_id();
        Long country_id = newTour.getCountry().getCountry_id();
        Boolean transfer_included = newTour.getTransfer_included();

        Object[] params = new Object[] {name, hotel_id, country_id, transfer_included};

        jdbcTemplate.update("INSERT INTO tours (name, hotel_id, country_id, transfer_included) VALUES (?, ?, ?, ?)", params);

        return newTour;
    }

    @Override
    public void update(Tour updateTour, Long id) {

        String name = updateTour.getName();
        Long hotel_id = updateTour.getHotel().getHotel_id();
        Boolean transfer_included = updateTour.getTransfer_included();

        Object[] params = new Object[] {name, hotel_id, transfer_included, id};


        jdbcTemplate.update("UPDATE tours SET name = ?, hotel_id = ?, transfer_included = ?" +
                " WHERE tour_id = ?", params);

    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM tours WHERE tour_id = ?", id);
    }
}
