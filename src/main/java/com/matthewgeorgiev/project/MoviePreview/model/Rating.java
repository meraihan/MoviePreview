package com.matthewgeorgiev.project.MoviePreview.model;


public class Rating {
    public Integer id;
    public User user;
    public String imdbID;
    public String rating;
    public Integer isFavourite;

    public Rating() {
    }

    public Rating(Integer id, User user, String imdbID, String rating, Integer isFavourite) {
        this.id = id;
        this.user = user;
        this.imdbID = imdbID;
        this.rating = rating;
        this.isFavourite = isFavourite;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Integer getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(Integer isFavourite) {
        this.isFavourite = isFavourite;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", user=" + user +
                ", imdbID='" + imdbID + '\'' +
                ", rating='" + rating + '\'' +
                ", isFavourite=" + isFavourite +
                '}';
    }
}
