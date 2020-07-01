package com.matthewgeorgiev.project.MoviePreview.service;

import com.matthewgeorgiev.project.MoviePreview.model.User;
import com.matthewgeorgiev.project.MoviePreview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;


@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    public Boolean loadUserByUsername(String username, String password) {
        Boolean isAuthenticated = Boolean.FALSE;
        final User user = userRepository.findByUsername(username);
        if (user != null) {
            if (password.equals(user.getPassword())){
                isAuthenticated = Boolean.TRUE;
            }
        }
        return isAuthenticated;
    }

    public boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }
}
