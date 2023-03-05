package com.example.springsecurity.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LoginResponse {
    private String type = "Bearer";
    private String uuid;
    private String userCode;
    private String username;
    private String email;
    private List<String> roles;
    private String token;

    public LoginResponse(
            String accessToken, String uuid,
            String username, String userCode,
            String email, List<String> roles)
    {
        this.uuid = uuid;
        this.username = username;
        this.userCode = userCode;
        this.email = email;
        this.roles = roles;
        this.token = accessToken;
    }
}
