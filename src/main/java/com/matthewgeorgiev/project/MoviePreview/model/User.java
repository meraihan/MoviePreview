package com.matthewgeorgiev.project.MoviePreview.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class User {
    public Integer id;
    public String username;
    public String name;
    public String password;
    public String phone;
    public String role;
    private MultipartFile image;
    private String imgLocation;
}
