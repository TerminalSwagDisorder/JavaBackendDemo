package com.server.JavaServer.model;

import com.server.JavaServer.validation.*;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserID")
	@JsonProperty("UserID")
	private Long userID;

	@NotBlank(message = "Name is required")
	@Size(min=3, max=50, message="Name must be between 3 and 50 characters")
	@Column(name = "Name")
	@JsonProperty("Name")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message="Invalid email format")
	@Column(name = "Email", unique = true)
	@JsonProperty("Email")
	private String email;

	@NotBlank(message = "Password is required")
	@NotNull
	@ValidPassword
	@Column(name = "Password")
	@JsonProperty("Password")
	private String password;

	@Size(max=6)
	@Column(name = "Gender")
	@JsonProperty("Gender")
	private String gender;

	@Column(name = "ProfileImage")
	@JsonProperty("ProfileImage")
	private String profileImage;

	@Column(name = "RoleID")
	@JsonProperty("RoleID")
	private Integer roleID;
}
