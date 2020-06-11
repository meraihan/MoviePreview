package com.matthewgeorgiev.project.MoviePreview.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Helper {
    public static PasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
}
