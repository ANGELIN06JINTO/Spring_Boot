package com.ty.ToDo.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ty.ToDo.entity.User;
import com.ty.ToDo.service.UserService;

@Controller
public class UserController {
    
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    
    // Login mapping removed to fix ambiguous mapping with AuthController
    
    // Signup mappings removed to fix ambiguous mapping with AuthController
    
    @GetMapping("/")
    public String home() {
        return "redirect:/todos";
    }
}