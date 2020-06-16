package com.matthewgeorgiev.project.MoviePreview.service;

import com.matthewgeorgiev.project.MoviePreview.model.User;
import com.matthewgeorgiev.project.MoviePreview.repository.UserRepository;
import com.matthewgeorgiev.project.MoviePreview.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Value("${location.image}")
    String imageLocation;

    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    public User addUser(User user) {
        try {
            byte[] bytes = user.getImage().getBytes();
            Path path = Paths.get(imageLocation + user.getImage().getOriginalFilename());
            Files.write(path, bytes);
            user.setImgLocation(path.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        user.setPassword(Helper.bCryptEncoder.encode(user.getPassword()));
        return userRepository.add(user);
    }
}
