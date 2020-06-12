package com.matthewgeorgiev.project.MoviePreview.controller;

import com.matthewgeorgiev.project.MoviePreview.model.Movie;
import com.matthewgeorgiev.project.MoviePreview.model.Rating;
import com.matthewgeorgiev.project.MoviePreview.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/movie")
@Controller
@Slf4j
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/search")
    public String searchPage() {
        return "movie/search";
    }

    @PostMapping("/search")
    public String findByTitle(@ModelAttribute("title") String title, Model model) {
        List<Movie> movies = movieService.findByTitle(title);
        log.info("Movie: {}", movies);
        model.addAttribute("movies", movies);
        return "movie/search";
    }

    @GetMapping("/rate/{imdbID}")
    public String displayRatingPage(Model model, @PathVariable String imdbID) {
        Movie movie = movieService.findByImdbId(imdbID);
        model.addAttribute("movie", movie);
        return "movie/ratings_page";
    }

    @PostMapping("/rate")
    public String rate(@ModelAttribute("movie") Movie movie) {
        if (movie != null && movie.getUserRating() != null) {
            Rating rating = movieService.saveMovieRating(movie);
            log.info("Rating: {}", rating);
        }
        return "redirect:/movie/rate/" + movie.getImdbID();
    }
}
