package com.matthewgeorgiev.project.MoviePreview.model;

import lombok.Data;

import java.util.List;

@Data
public class SearchResults {
    public List<Movie> Search;
    public String totalResults;
    public String Response;
}
