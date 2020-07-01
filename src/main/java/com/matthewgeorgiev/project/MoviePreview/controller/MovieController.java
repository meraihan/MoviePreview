package com.matthewgeorgiev.project.MoviePreview.controller;

import com.matthewgeorgiev.project.MoviePreview.model.Movie;
import com.matthewgeorgiev.project.MoviePreview.model.Rating;
import com.matthewgeorgiev.project.MoviePreview.service.AuthenticationService;
import com.matthewgeorgiev.project.MoviePreview.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.logging.Logger;

@RequestMapping("/movie")
@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private AuthenticationService authenticationService;
    private static final Logger log = Logger.getLogger(String.valueOf(MovieController.class));

    @GetMapping("")
    public String handleReq(HttpSession session) {
        if (!authenticationService.isLoggedIn(session)) {
            return "redirect:login";
        }
        return "redirect:/";
    }

    @GetMapping("/search")
    public String searchPage(HttpSession session) {
        if(authenticationService.isLoggedIn(session)){
            return "movie/search";
        }
        return "redirect:/";
    }

    @PostMapping("/search")
    public String findByTitle(@ModelAttribute("title") String title, Model model, HttpSession session) {
        if (authenticationService.isLoggedIn(session)){
            List<Movie> movies = movieService.findByTitle(title);
            log.info("Movie: "+ movies);
            model.addAttribute("movies", movies);
            return "movie/search";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/ratings")
    public String allRatings(Model model, HttpSession session) {
        if(authenticationService.isLoggedIn(session)){
            List<Movie> movies = movieService.findMoviesByIMDBId();
            model.addAttribute("movies", movies);
            return "movie/rating_list";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/rate/{imdbID}")
    public String displayRatingPage(Model model, @PathVariable String imdbID, HttpSession session) {
        if (authenticationService.isLoggedIn(session)){
            Movie movie = movieService.findByImdbId(imdbID);
            model.addAttribute("movie", movie);
            return "movie/ratings_page";
        } else {
            return "redirect:/";
        }

    }

    @PostMapping("/rate")
    public String rate(@ModelAttribute("movie") Movie movie, HttpSession session) {
        if (authenticationService.isLoggedIn(session)){
            if (movie != null && movie.getUserRating() != null) {
                Rating rating = movieService.saveMovieRating(movie);
                log.info("Rating: "+ rating);
            }
            return "redirect:/movie/rate/" + movie.getImdbID();
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/myfav")
    public String myfav(Model model, HttpSession session) {
        if(authenticationService.isLoggedIn(session)){
            List<Movie> movies = movieService.findMyFav();
            model.addAttribute("movies", movies);
            return "movie/favourite";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/remove-fav")
    public String findIssueForAutoComplete(@RequestParam("imdb") String imdbId, HttpSession session) {
        if(authenticationService.isLoggedIn(session)){
            if (movieService.updateFavourite(imdbId)) {
                return "redirect:myfav";
            }

        }
        return "redirect:/";
    }


}
