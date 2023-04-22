package com.example.springsecurity.dto.requests;

import com.example.springsecurity.dto.response.LoginResponse;
import io.cqrs.query.IQuery;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class LoginRequest implements IQuery<LoginResponse> {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
