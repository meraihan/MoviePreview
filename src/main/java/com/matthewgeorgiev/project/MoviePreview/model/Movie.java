package com.matthewgeorgiev.project.MoviePreview.model;


public class Movie {
    public String imdbID;
    public String Title;
    public String Year;
    public String ImdbRating;
    public String userRating;
    public String Poster;
    public Boolean isFavourite;
    public String userName;

    public Movie() {
    }

    public Movie(String imdbID, String title, String year, String imdbRating, String userRating, String poster, Boolean isFavourite, String userName) {
        this.imdbID = imdbID;
        Title = title;
        Year = year;
        ImdbRating = imdbRating;
        this.userRating = userRating;
        Poster = poster;
        this.isFavourite = isFavourite;
        this.userName = userName;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getImdbRating() {
        return ImdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.ImdbRating = imdbRating;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public Boolean getFavourite() {
        return isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        isFavourite = favourite;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imdbID='" + imdbID + '\'' +
                ", Title='" + Title + '\'' +
                ", Year='" + Year + ImdbRating + '\'' +
                ", userRating='" + userRating + '\'' +
                ", Poster='" + Poster + '\'' +
                ", isFavourite=" + isFavourite +
                ", userName='" + userName + '\'' +
                '}';
    }
}
