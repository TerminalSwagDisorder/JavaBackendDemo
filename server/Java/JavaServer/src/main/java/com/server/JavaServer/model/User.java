package com.server.JavaServer.model;

import lombok.*;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UserID")
	@JsonProperty("UserID")
	private Long userID;

	@Column(name = "Name")
	@JsonProperty("Name")
	private String name;

	@Column(name = "Email", unique = true)
	@JsonProperty("Email")
	private String email;

	@Column(name = "Password")
	@JsonProperty("Password")
	private String password;

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
