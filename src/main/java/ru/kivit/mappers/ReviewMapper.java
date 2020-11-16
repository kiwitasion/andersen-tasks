package ru.kivit.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kivit.models.Hotel;
import ru.kivit.models.Review;
import ru.kivit.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet rs, int i) throws SQLException {
        final Review review = new Review();

        review.setReview_id(rs.getLong("review_id"));
        review.setText(rs.getString("text"));
        Hotel hotel = new Hotel();
        hotel.setHotel_id(rs.getLong("hotel_id"));
        review.setHotel(hotel);
        User user = new User();
        user.setUser_id(rs.getLong("user_id"));
        review.setUser(user);

        return review;
    }
}
