package com.matthewgeorgiev.project.MoviePreview.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class User {
    public Integer id;
    public String username;
    public String name;
    public String password;
    public String confirmPassword;
    public String role;
    private MultipartFile image;
    private String imgLocation;
    private LocalDateTime lastLoginAt;
    private LocalDateTime createdAt;
}
