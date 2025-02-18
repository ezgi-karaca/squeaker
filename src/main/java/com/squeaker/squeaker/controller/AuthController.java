package com.squeaker.squeaker.controller;

import com.squeaker.squeaker.entity.User;
import com.squeaker.squeaker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    public String register(@RequestBody User user) {

        authService.register(user.getUsername(), user.getEmail(), user.getPassword());
        return "Registration Successful!";
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = authService.login(username, password);

        return "Login Successful!";
    }



}
