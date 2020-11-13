package ru.kivit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kivit.models.Review;



@Repository
public interface ReviewRepository  extends JpaRepository<Review, Long> {
}