package com.matthewgeorgiev.project.MoviePreview.model;


import java.util.List;

public class SearchResults {
    public List<Movie> Search;
    public String totalResults;
    public String Response;

    public SearchResults() {
    }

    public SearchResults(List<Movie> search, String totalResults, String response) {
        Search = search;
        this.totalResults = totalResults;
        Response = response;
    }

    public List<Movie> getSearch() {
        return Search;
    }

    public void setSearch(List<Movie> search) {
        Search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    @Override
    public String toString() {
        return "SearchResults{" +
                "Search=" + Search +
                ", totalResults='" + totalResults + '\'' +
                ", Response='" + Response + '\'' +
                '}';
    }
}
