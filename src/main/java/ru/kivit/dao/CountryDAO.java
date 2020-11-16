package ru.kivit.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.kivit.mappers.CountryMapper;
import ru.kivit.models.Country;

import java.util.List;

public class CountryDAO implements BaseDAO<Country> {

    private JdbcTemplate jdbcTemplate;

    public CountryDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Country> findAll() {

        return jdbcTemplate.query("SELECT * FROM countries", new CountryMapper());
    }

    @Override
    public Country findById(Long id) {

        Object[] country = new Object[] {id};

        return jdbcTemplate.queryForObject("SELECT * FROM countries WHERE country_id = ?", country, new CountryMapper());

    }

    @Override
    public Country save(Country newCountry) {

        String name = newCountry.getName();

        Object[] params = new Object[] {name};

        jdbcTemplate.update("INSERT INTO countries (name) VALUES (?)", params);

        return newCountry;
    }

    @Override
    public void update(Country updateCountry, Long id) {
        String name = updateCountry.getName();

        Object[] params = new Object[] {name, id};

        jdbcTemplate.update("UPDATE countries SET name = ? WHERE country_id = ?", params);

    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM countries WHERE country_id = ?", id);
    }

}
