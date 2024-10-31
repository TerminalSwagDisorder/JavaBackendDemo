package com.server.JavaServer.service;

import com.server.JavaServer.model.User;
import com.server.JavaServer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import java.util.Optional;
import java.util.List;


@Service
public class UserService {
 @Autowired
 private UserRepository userRepository;

 @Autowired
 private BCryptPasswordEncoder passwordEncoder;

 @Transactional
 public User registerUser(@Valid User user) throws Exception {
     // Check if email exists
     if(userRepository.findByEmail(user.getEmail()).isPresent()){
         throw new Exception("Email already in use");
     }
     // Encode password
     user.setPassword(passwordEncoder.encode(user.getPassword()));
     // Set default roleID
     user.setRoleID(1); // Adjust as needed
     return userRepository.save(user);
 }

 public User loginUser(String email, String password) throws Exception {
     User user = userRepository.findByEmail(email)
         .orElseThrow(() -> new Exception("Invalid email or password"));
     if(passwordEncoder.matches(password, user.getPassword())){
         return user;
     } else {
         throw new Exception("Invalid email or password");
     }
 }

 public Optional<User> getUserById(Long id){
     return userRepository.findById(id);
 }
 
 public List<User> findAllUsers() {
	    return userRepository.findAll();
	}

 // Additional methods for updating user, etc.
}
