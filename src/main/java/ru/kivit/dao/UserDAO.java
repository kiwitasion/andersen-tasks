package ru.kivit.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.kivit.mappers.UserMapper;
import ru.kivit.models.User;

import java.util.List;

public class UserDAO implements BaseDAO<User> {

    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {

        return jdbcTemplate.query("SELECT * FROM users", new UserMapper());
    }

    @Override
    public User findById(Long id) {

        Object[] user = new Object[] {id};

        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id = ?", user, new UserMapper());
    }

    @Override
    public User save(User newUser) {

        String first_name = newUser.getFirst_name();
        String last_name = newUser.getLast_name();
        String login = newUser.getLogin();
        String password = newUser.getPassword();

        Object[] params = new Object[] {first_name, last_name, login, password};

        jdbcTemplate.update("INSERT INTO Users (first_name, last_name, login, password) VALUES (?, ?, ?, ?)", params);

        return newUser;
    }

    @Override
    public void update(User updateUser, Long id) {

        String first_name = updateUser.getFirst_name();
        String last_name = updateUser.getLast_name();
        String login = updateUser.getLogin();
        String password = updateUser.getPassword();

        Object[] params = new Object[] {first_name, last_name, login, password, id};

        jdbcTemplate.update("UPDATE users SET first_name = ?, last_name = ?, login = ?, password = ?" +
                " WHERE user_id = ?", params);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE user_id = ?", id);
    }
}
