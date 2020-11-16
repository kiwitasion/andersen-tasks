package ru.kivit.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.kivit.mappers.ReviewMapper;
import ru.kivit.models.Review;

import java.util.List;

public class ReviewDAO implements BaseDAO<Review> {

    private final JdbcTemplate jdbcTemplate;

    public ReviewDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Review> findAll() {

        return jdbcTemplate.query("SELECT * FROM reviews", new ReviewMapper());
    }

    @Override
    public Review findById(Long id) {

        Object[] review = new Object[] {id};

        return jdbcTemplate.queryForObject("SELECT * FROM reviews WHERE review_id = ?", review, new ReviewMapper());
    }

    @Override
    public Review save(Review newReview) {

        String text = newReview.getText();
        Long user_id = newReview.getUser().getUser_id();
        Long hotel_id = newReview.getHotel().getHotel_id();

        Object[] params = new Object[] {text, user_id, hotel_id};

        jdbcTemplate.update("INSERT INTO reviews (text, user_id, hotel_id) VALUES (?, ?, ?)", params);

        return newReview;
    }

    @Override
    public void update(Review updateReview, Long id) {

        String text = updateReview.getText();

        Object[] params = new Object[] {text, id};

        jdbcTemplate.update("UPDATE reviews SET text = ? WHERE review_id = ?", params);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM reviews WHERE review_id = ?", id);
    }
}
