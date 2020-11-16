package ru.kivit.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kivit.models.Country;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryMapper implements RowMapper<Country> {

    @Override
    public Country mapRow(ResultSet rs, int i) throws SQLException {
        final Country country = new Country();

        country.setCountry_id(rs.getLong("country_id"));
        country.setName(rs.getString("name"));
        return country;
    }
}
