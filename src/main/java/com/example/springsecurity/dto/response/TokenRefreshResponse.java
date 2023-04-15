package com.example.springsecurity.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRefreshResponse {
    private String refreshToken;

    public TokenRefreshResponse(String refreshToken) {
        this.refreshToken = "Bearer " + refreshToken;
    }

}
