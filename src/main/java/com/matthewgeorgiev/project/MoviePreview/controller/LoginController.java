package com.matthewgeorgiev.project.MoviePreview.controller;

import com.matthewgeorgiev.project.MoviePreview.model.User;
import com.matthewgeorgiev.project.MoviePreview.service.AuthenticationService;
import com.matthewgeorgiev.project.MoviePreview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String dashboard(HttpSession session) {
        if(!authenticationService.isLoggedIn(session)){
            return "login/login";
        }
        return "dashboard/dashboard";
    }

    @GetMapping("/login")
    public String getLogin(HttpSession session) {
        if (authenticationService.isLoggedIn(session)) {
            return "redirect:dashboard";
        }
        return "login/login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") User user, Model model, HttpSession session,  HttpServletResponse response) {
        if (user != null && authenticationService.loadUserByUsername(user.getUsername(), user.getPassword())) {
            session.setAttribute("user", user);
            Cookie cookie = new Cookie("username", user.getUsername());
            cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
            response.addCookie(cookie);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Username or Password is invalid!");
            return "login/login";
        }
    }

    @GetMapping("/logout")
    public String postLogout(HttpSession session) {
        session.setAttribute("user", null);
        return "redirect:/";
    }

    @GetMapping("/404")
    public String error() {
        return "error/404";
    }

}
