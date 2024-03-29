package com.matthewgeorgiev.project.MoviePreview.service;

import com.matthewgeorgiev.project.MoviePreview.model.Movie;
import com.matthewgeorgiev.project.MoviePreview.model.Rating;
import com.matthewgeorgiev.project.MoviePreview.model.SearchResults;
import com.matthewgeorgiev.project.MoviePreview.model.User;
import com.matthewgeorgiev.project.MoviePreview.repository.RatingRepository;
import com.matthewgeorgiev.project.MoviePreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class MovieService {
    @Value("${remote.api.url}")
    private String url;
    @Value("${api.key}")
    private String key;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RatingRepository ratingRepository;
    private static final Logger log = Logger.getLogger(String.valueOf(MovieService.class));

    public List<Movie> findByTitle(String title) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("just_a_sample_header", "just_a_simple_value");
        HttpEntity entity = new HttpEntity(headers);
        String url = this.url + "&s=" + title.trim();
        ResponseEntity<SearchResults> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, SearchResults.class);
        log.info("Status: "+ responseEntity.getStatusCode());
        SearchResults results = responseEntity.getBody();
        List<Movie> movies = results.getSearch();
        return movies;
    }

    public Movie findByImdbId(String id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        HttpEntity entity = new HttpEntity(headers);
        String url = this.url + "&i=" + id.trim();
        ResponseEntity<Movie> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Movie.class);
        log.info("Status: "+ responseEntity.getStatusCode());
        Movie movie = responseEntity.getBody();
        User user = userRepository.findByUsername("admin");
        Rating rating = ratingRepository.findRatingByUserIdAndImdbId(user.getId(), movie.getImdbID());
        if (rating.getId()!=null) {
            movie.setUserRating(rating.getRating());
            movie.setIsFavourite(rating.getIsFavourite());
        } else {
            movie.setIsFavourite(1);
        }
        return movie;
    }

    public List<Movie> findMoviesByIMDBId() {
        List<Movie> movies = new ArrayList<>();
        List<Rating> ratings = ratingRepository.findAll();
        ratings.stream().forEach(rating -> {
            RestTemplate restTemplate = restTemplateBuilder.build();
            HttpHeaders headers = new HttpHeaders();
            HttpEntity entity = new HttpEntity(headers);
            String url = this.url + "&i=" + rating.getImdbID().trim();
            ResponseEntity<Movie> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Movie.class);
            log.info("Status: "+ responseEntity.getStatusCode());
            Movie movie = responseEntity.getBody();
            User user = userRepository.findByUserId(rating.getUser().getId());
            movie.setUserName(user.getName());
            movie.setUserRating(rating.getRating());
            movies.add(movie);
        });
        return movies;
    }

    public Rating saveMovieRating(Movie movie) {
        User user = userRepository.findByUsername("admin");
        Rating rating = ratingRepository.findRatingByUserIdAndImdbId(user.getId(), movie.getImdbID());
        if (rating.getId() == null) {
            rating.setImdbID(movie.getImdbID());
            rating.setRating(movie.getUserRating());
            rating.setUser(user);
            rating.setIsFavourite(movie.getIsFavourite());
            rating = ratingRepository.addRating(rating);
        } else {
            rating.setRating(movie.getUserRating());
            boolean success = ratingRepository.updateRating(rating);
            log.info("Updated Rating status: "+ success);
        }
        return rating;
    }

    public List<Movie> findMyFav() {
        List<Movie> movies = new ArrayList<>();
        List<Rating> ratings = new ArrayList<>();
        User user = userRepository.findByUsername("admin");
        ratings = ratingRepository.findByUserId(user.getId());
        ratings.stream().forEach(rating -> {
            RestTemplate restTemplate = restTemplateBuilder.build();
            HttpHeaders headers = new HttpHeaders();
            HttpEntity entity = new HttpEntity(headers);
            String url = this.url + "&i=" + rating.getImdbID().trim();
            ResponseEntity<Movie> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, Movie.class);
            log.info("Status: "+ responseEntity.getStatusCode());
            Movie movie = responseEntity.getBody();
            movie.setUserName(user.getName());
            movie.setIsFavourite(rating.getIsFavourite());
            movie.setUserRating(rating.getRating());
            movies.add(movie);
        });
        return movies;
    }

    public boolean updateFavourite(String imdbId) {

        return ratingRepository.updateFav(imdbId);
    }
}
