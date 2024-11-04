package com.server.JavaServer.dto;

import com.server.JavaServer.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileResponseDTO {
    private String message;
    private User userData;
}
