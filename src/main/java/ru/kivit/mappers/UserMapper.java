package ru.kivit.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.kivit.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int i) throws SQLException {
        final User user = new User();

        user.setUser_id(rs.getLong("user_id"));
        user.setFirst_name(rs.getString("first_name"));
        user.setLast_name(rs.getString("last_name"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));

        return user;
    }
}
