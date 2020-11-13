package ru.kivit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kivit.models.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
}
