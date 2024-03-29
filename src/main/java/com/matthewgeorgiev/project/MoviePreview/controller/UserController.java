package com.matthewgeorgiev.project.MoviePreview.controller;

import com.matthewgeorgiev.project.MoviePreview.model.User;
import com.matthewgeorgiev.project.MoviePreview.service.AuthenticationService;
import com.matthewgeorgiev.project.MoviePreview.service.UserService;
import com.matthewgeorgiev.project.MoviePreview.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/find")
    public String findUserPageDisplay(HttpSession session) {
        if(authenticationService.isLoggedIn(session)){
            return "user/search";
        } else {
            return "redirect:/";
        }
    }


    @PostMapping("/find")
    public String findUserByusername(@ModelAttribute("username") String username, Model model, HttpSession session) {
        if(authenticationService.isLoggedIn(session)){
            User user = userService.getUser(username);
            if (user != null) {
                List<User> users = new ArrayList<>();
                users.add(user);
                model.addAttribute("users", users);
            }
            return "user/all";
        } else {
            return "redirect:/";
        }
    }


    @GetMapping("/register")
    public String showRegistrationPage(Model model, HttpSession session) {
        List<String> roles = new ArrayList<>();
        roles.add("USER");
        roles.add("ADMIN");
        model.addAttribute("roles", roles);
        model.addAttribute("user", new User());
        model.addAttribute("sessions", session.getAttribute("user"));
        model.addAttribute("userRole", session.getAttribute("role"));
        return "user/add";
    }


    @PostMapping("/register")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("image") MultipartFile image, Model model,
                          final RedirectAttributes redirectAttributes) {
        if (user.getRole() == null) {
            user.setRole("USER");
        }
        user = userService.addUser(user);
        if (user != null) {
            redirectAttributes.addFlashAttribute("success", "Please log in");
            return "redirect:/";
        } else {
            return "redirect:/user/register";
        }
    }

    @GetMapping("/list")
    public String findAllUsers(Model model, HttpSession session) {
        if(authenticationService.isLoggedIn(session)){
            List<User> list;
            list = userService.getAllUsers();
            model.addAttribute("users", list);
            return "user/all";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/delete")
    public String delete( @ModelAttribute("user") User user, final RedirectAttributes redirectAttributes, HttpSession session) {
        if(authenticationService.isLoggedIn(session)){
            if (userService.delete(user.getId())) {
                redirectAttributes.addFlashAttribute("success", "Successfully Deleted User..");
            } else {
                redirectAttributes.addFlashAttribute("error", "Deletion Failed !");
            }
            return "redirect:list";
        } else {
            return "redirect:/";
        }

    }

    @GetMapping("/about-us")
    public String about(HttpSession session) {
        if (authenticationService.isLoggedIn(session)){
            return "about/about_us";
        } else {
            return "redirect:/";
        }
    }

}
