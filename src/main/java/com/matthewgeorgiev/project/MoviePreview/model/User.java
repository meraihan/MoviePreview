package com.matthewgeorgiev.project.MoviePreview.model;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

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

    public User() {
    }

    public User(Integer id, String username, String name, String password, String confirmPassword, String role, MultipartFile image, String imgLocation, LocalDateTime lastLoginAt) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.role = role;
        this.image = image;
        this.imgLocation = imgLocation;
        this.lastLoginAt = lastLoginAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImgLocation() {
        return imgLocation;
    }

    public void setImgLocation(String imgLocation) {
        this.imgLocation = imgLocation;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", role='" + role + '\'' +
                ", image=" + image +
                ", imgLocation='" + imgLocation + '\'' +
                ", lastLoginAt=" + lastLoginAt +
                '}';
    }
}
