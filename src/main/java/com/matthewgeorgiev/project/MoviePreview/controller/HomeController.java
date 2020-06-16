package com.matthewgeorgiev.project.MoviePreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String landingPage() {
        return "login/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard/dashboard";
    }

    @GetMapping("/about-us")
    public String about() {
        return "about/about_us";
    }
}
