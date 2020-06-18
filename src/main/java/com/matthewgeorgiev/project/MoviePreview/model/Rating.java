package com.matthewgeorgiev.project.MoviePreview.model;

import lombok.Data;

@Data
public class Rating {
    public Integer id;
    public User user;
    public String imdbID;
    public String rating;
}
