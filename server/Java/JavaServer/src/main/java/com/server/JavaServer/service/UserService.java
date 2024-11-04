package com.server.JavaServer.service;

import com.server.JavaServer.model.User;
import com.server.JavaServer.dto.UserDTO;
import com.server.JavaServer.mapper.UserMapper;
import com.server.JavaServer.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User registerUser(UserDTO userDTO) throws Exception {
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()){
            throw new Exception("Email already in use");
        }
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoleID(1); // Guest = 1
        return userRepository.save(user);
    }

    public User loginUser(String Email, String Password) throws Exception {
        User user = userRepository.findByEmail(Email)
        .orElseThrow(() -> new Exception("Invalid email or password"));
        if(passwordEncoder.matches(Password, user.getPassword())){
            return user;
        } else {
            throw new Exception("Invalid email or password");
            }
    }

    public User updateUser(Long userId, UserDTO userDTO) throws Exception {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User userToUpdate = existingUser.get();

            userMapper.updateUserFromDto(userDTO, userToUpdate);

            if (userDTO.getPassword() != null) {
                userToUpdate.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }

            return userRepository.save(userToUpdate);
        } else {
            throw new Exception("User not found");
        }
    }



    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

}
