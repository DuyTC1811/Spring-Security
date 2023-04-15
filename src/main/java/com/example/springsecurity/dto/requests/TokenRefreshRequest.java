package com.example.springsecurity.dto.requests;

import com.example.springsecurity.dto.response.TokenRefreshResponse;
import io.cqrs.command.ICommand;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenRefreshRequest implements ICommand<TokenRefreshResponse> {
    private String username;

}
