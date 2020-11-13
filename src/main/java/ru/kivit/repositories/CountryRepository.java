package ru.kivit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kivit.models.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
