package com.matthewgeorgiev.project.MoviePreview.controller;

import com.matthewgeorgiev.project.MoviePreview.model.Movie;
import com.matthewgeorgiev.project.MoviePreview.model.Rating;
import com.matthewgeorgiev.project.MoviePreview.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RequestMapping("/movie")
@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;
    private static final Logger log = Logger.getLogger(String.valueOf(MovieController.class));
    @GetMapping("/search")
    public String searchPage() {
        return "movie/search";
    }

    @PostMapping("/search")
    public String findByTitle(@ModelAttribute("title") String title, Model model) {
        List<Movie> movies = movieService.findByTitle(title);
        log.info("Movie: "+ movies);
        model.addAttribute("movies", movies);
        return "movie/search";
    }

    @GetMapping("/ratings")
    public String allRatings(Model model) {
        List<Movie> movies = movieService.findMoviesByIMDBId();
        model.addAttribute("movies", movies);
        return "movie/rating_list";
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
            log.info("Rating: "+ rating);
        }
        return "redirect:/movie/rate/" + movie.getImdbID();
    }

    @GetMapping("/myfav")
    public String myfav(Model model) {
        List<Movie> movies = movieService.findMyFav();
        model.addAttribute("movies", movies);
        return "movie/favourite";
    }

    @GetMapping("/remove-fav")
    public String findIssueForAutoComplete(@RequestParam("imdb") String imdbId) {
        if (movieService.updateFavourite(imdbId)) {
            return "redirect:myfav";
        }
        return "redirect:/";
    }


}
