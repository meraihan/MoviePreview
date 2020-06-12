package com.matthewgeorgiev.project.MoviePreview.model;

import lombok.Data;

@Data
public class Movie {
    public String imdbID;
    public String Title;
    public String Year;
    public String imdbRating;
    public String userRating;
    public String Poster;
}
