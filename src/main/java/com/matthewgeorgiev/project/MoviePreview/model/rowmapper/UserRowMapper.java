package com.matthewgeorgiev.project.MoviePreview.model.rowmapper;

import com.matthewgeorgiev.project.MoviePreview.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setPhone(resultSet.getString("phone"));
        user.setId(resultSet.getInt("id"));
        user.setRole(resultSet.getString("role"));
        return user;
    }
}
