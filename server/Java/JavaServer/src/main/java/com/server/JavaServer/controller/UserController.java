package com.server.JavaServer.controller;

import com.server.JavaServer.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.JavaServer.dto.*;
import com.server.JavaServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired private ObjectMapper objectMapper;
	
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User registerUser(@Valid @RequestBody UserDTO userDTO) throws Exception {
        return userService.registerUser(userDTO);
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody User loginRequest, HttpSession session) throws Exception {
        User user = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        session.setAttribute("user", user);
        return user;
    }

    @GetMapping("/profile")
    public ProfileResponseDTO getProfile(HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new Exception("Not authenticated");
        }

        // Refresh user data from the database (optional)
        Optional<User> updatedUser = userService.getUserById(user.getUserID());
        if (updatedUser.isPresent()) {
            session.setAttribute("user", updatedUser.get());
            return new ProfileResponseDTO("Authenticated", updatedUser.get());
        } else {
            throw new Exception("User not found");
        }
    }

    @PatchMapping("/profile/update")
    public ProfileResponseDTO patchProfile(@RequestBody Map<String, Object> profileData, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new Exception("Not authenticated");
        }

        UserDTO userDTO = objectMapper.convertValue(profileData, UserDTO.class);
        User updatedUser = userService.updateUser(user.getUserID(), userDTO);

        session.setAttribute("user", updatedUser);
        return new ProfileResponseDTO("Profile updated successfully", updatedUser);
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
