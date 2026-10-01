package com.aarohi.subsense.controller;

import com.aarohi.subsense.dto.UserResponseDTO;
import com.aarohi.subsense.entity.User;
import com.aarohi.subsense.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {            //signup entity

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public UserResponseDTO registerUser(@Valid @RequestBody User user) { //Spring receives this JSON and, because of @RequestBody, converts it into a Java User object.
        return userService.registerUser(user);
    }
    @PostMapping("/login")
    public String loginUser(@RequestBody User user) { //made changes from User to String

        return userService.loginUser(
                user.getEmail(),
                user.getPassword()
        );
    }
    @GetMapping("/profile")
    public String profile() {
        return "You are authenticated";
    }
}