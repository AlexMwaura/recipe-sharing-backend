package com.recipe.sharing.controllers;

import com.recipe.sharing.dto.LoginRequest;
import com.recipe.sharing.dto.LoginResponse;
import com.recipe.sharing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
