package com.matthewgeorgiev.project.MoviePreview.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Rating {
    public Integer id;
    public User user;
    public String imdbID;
    public String rating;
    public LocalDateTime createdAt;
}
