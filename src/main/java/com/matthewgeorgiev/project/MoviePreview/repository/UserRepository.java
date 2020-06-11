package com.matthewgeorgiev.project.MoviePreview.repository;

import com.matthewgeorgiev.project.MoviePreview.model.User;
import com.matthewgeorgiev.project.MoviePreview.model.rowmapper.UserRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User findByUsername(String username) {
        User user;
        String query = "Select * from user where username like ? order by id desc limit 1";

        try {
            user = jdbcTemplate.queryForObject(query, new Object[] {username}, new UserRowMapper());
            return user;
        } catch (DataAccessException dae) {
            log.error("An exception occurred when executing the following query:");
            log.error(query.replace("?", username));
            log.error(dae.getLocalizedMessage());
        }
        return null;
    }

}
