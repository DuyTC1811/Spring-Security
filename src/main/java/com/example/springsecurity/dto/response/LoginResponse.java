package com.example.springsecurity.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = "Bearer " + token;
    }

}
