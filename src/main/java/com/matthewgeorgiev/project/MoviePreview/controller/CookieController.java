package com.matthewgeorgiev.project.MoviePreview.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cookie")
public class CookieController {

    @GetMapping("")
    public String readCookie(@CookieValue(value = "username") String username,
                             Model model, HttpServletResponse response) {
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);

        cookie.setSecure(true);
        response.addCookie(cookie);
        model.addAttribute("userName", username);
        return "cookie/cookies";
    }

    @GetMapping("/all-cookies")
    public String readAllCookies(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            model.addAttribute("cookies", Arrays.stream(cookies)
                    .map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", ")));
        } else {
            model.addAttribute("cookies", "No Cookies");
        }
        return "cookie/cookies";
    }
}
