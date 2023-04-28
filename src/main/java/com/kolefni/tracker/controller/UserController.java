package com.kolefni.tracker.controller;

import com.kolefni.tracker.dto.UserDTO;
import com.kolefni.tracker.model.User;
import com.kolefni.tracker.repository.UserRepository;
import com.kolefni.tracker.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    private final UserServiceImpl userService;

    public UserController(UserRepository userRepo,
                          UserServiceImpl userService) {
        this.userRepo = userRepo;
        this.userService = userService;
    }

    @GetMapping("/")
    public String viewLandingPage() {
        return "index";
    }

    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        return "signup";
    }

    @GetMapping("/login")
    String showLoginForm() {
        return "login";
    }

    @GetMapping("/user")
    public String showUserInterface(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute("userDTO")UserDTO userDTO,
                              Model model) {
            model.addAttribute("userDTO", userDTO);
            return userService.checkIfUserExists(userDTO);
    }
}
