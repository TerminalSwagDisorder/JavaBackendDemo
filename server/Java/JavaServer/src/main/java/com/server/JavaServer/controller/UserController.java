package com.server.JavaServer.controller;

import com.server.JavaServer.model.User;
import com.server.JavaServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User registerUser(@Valid @RequestBody User user) throws Exception {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User loginRequest, HttpSession session) throws Exception {
        User user = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        session.setAttribute("user", user);
        return user;
    }

    @GetMapping("/profile")
    public User getProfile(HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        if(user == null){
            throw new Exception("Not authenticated");
        }
            return user;
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "Logged out successfully";
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }
}
