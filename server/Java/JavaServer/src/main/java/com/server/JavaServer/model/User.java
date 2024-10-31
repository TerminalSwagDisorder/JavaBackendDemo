package com.server.JavaServer.model;

import com.server.JavaServer.validation.*;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer userID;

 @NotBlank(message = "Name is required")
 @NotNull
 @Size(min=3, max=50, message="Name must be between 3 and 50 characters")
 private String name;

 @NotBlank(message = "Email is required")
 @NotNull
 @Email(message="Invalid email format")
 @Column(unique = true)
 private String email;

 @NotBlank(message = "Password is required")
 @NotNull
 @Size(min=9, message="Password must be at least 9 characters")
 @ValidPassword
 private String password;

 @Size(max=6)
 private String gender;

 private String profileImage;

 private Integer roleID;
}
