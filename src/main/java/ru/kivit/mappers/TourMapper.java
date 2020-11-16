package ru.kivit.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kivit.dao.HotelDAO;
import ru.kivit.models.Country;
import ru.kivit.models.Hotel;
import ru.kivit.models.Tour;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TourMapper implements RowMapper<Tour> {
    @Override
    public Tour mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final Tour tour = new Tour();

        tour.setTour_id(rs.getLong("tour_id"));
        tour.setName(rs.getString("name"));
        Hotel hotel = new Hotel();
        hotel.setHotel_id(rs.getLong("hotel_id"));
        tour.setHotel(hotel);
        Country country = new Country();
        country.setCountry_id(rs.getLong("country_id"));
        tour.setCountry(country);
        tour.setTransfer_included(rs.getBoolean("transfer_included"));

        return tour;
    }
}
