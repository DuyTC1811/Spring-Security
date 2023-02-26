package com.example.springsecurity.dto.requests;

import com.example.springsecurity.dto.response.LoginResponse;
import io.cqrs.query.IQuery;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class LoginRequest implements IQuery<LoginResponse> {
    @NotBlank private String username;
    @NotBlank private String password;
}
