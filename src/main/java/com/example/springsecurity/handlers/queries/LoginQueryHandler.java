package com.example.springsecurity.handlers.queries;

import com.example.springsecurity.configuration.jwt.JwtUtils;
import com.example.springsecurity.dto.requests.LoginRequest;
import com.example.springsecurity.dto.response.LoginResponse;
import io.cqrs.query.IQueryHandler;
import io.exceptions.models.UserPasswordException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LoginQueryHandler implements IQueryHandler<LoginResponse, LoginRequest> {
    private final JwtUtils jwtUtils;

    @Value("${spring.security.jwtRefreshExpirationMs}")
    private Integer refreshTokenDurationMs;

    public LoginQueryHandler(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    public LoginResponse handler(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        if (username == null || password == null) {
            throw new UserPasswordException(username, password);
        }
        String jwt = jwtUtils.generateJwtToken(username, password);
        return new LoginResponse(jwt);
    }
}
