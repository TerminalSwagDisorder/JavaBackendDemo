package com.server.JavaServer.dto;

import com.server.JavaServer.model.User;
import com.server.JavaServer.validation.ValidPassword;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
	@Size(min=3, max=50, message="Name must be between 3 and 50 characters")
    @JsonProperty("Name")
    @NotBlank(message = "Name is required")
    private String name;

    @JsonProperty("Email")
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @JsonProperty("Password")
    @NotBlank(message = "Password is required")
    @ValidPassword
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
